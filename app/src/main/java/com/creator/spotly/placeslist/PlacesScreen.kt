package com.creator.spotly.placeslist

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.creator.spotly.BuildConfig
import com.creator.spotly.R
import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.placeslist.components.PlaceCard
import com.creator.spotly.ui.state.UiState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.lang.SecurityException

@Composable
fun PlacesScreen(
    type: String,
    title: String,
    imageRes: Int? = null,
    onBackClick: () -> Unit = {},
    onPlaceClick: (PlaceItem) -> Unit,
    vm: PlacesListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = (context as? ComponentActivity)
        ?: throw IllegalStateException("PlacesScreen must be hosted in a ComponentActivity")
    val fused: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    var userLat by remember { mutableStateOf<Double?>(null) }
    var userLng by remember { mutableStateOf<Double?>(null) }
    var locationRequested by remember { mutableStateOf(false) }
    var permissionDenied by remember { mutableStateOf(false) }

    // Single-permission launcher (we can ask for fine first, coarse fallback)
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val fineGranted = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (fineGranted || coarseGranted) {
            permissionDenied = false
            // safe-get location, protected by try/catch below
            requestLocationSafely(fused) { lat, lng ->
                userLat = lat
                userLng = lng
            }
        } else {
            permissionDenied = true
        }
    }

    // Helper to check if we already have permission
    fun hasLocationPermission(): Boolean {
        val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return fine == PackageManager.PERMISSION_GRANTED || coarse == PackageManager.PERMISSION_GRANTED
    }

    // Request permission if not available when entering composition
    LaunchedEffect(Unit) {
        if (hasLocationPermission()) {
            // we already have permission: fetch location immediately
            requestLocationSafely(fused) { lat, lng ->
                userLat = lat
                userLng = lng
            }
        } else {
            // ask for permission
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    val state by vm.state.collectAsState()

    // Trigger loading when location is available (only once per location)
    LaunchedEffect(userLat, userLng) {
        val lat = userLat
        val lng = userLng
        if (!locationRequested && lat != null && lng != null) {
            locationRequested = true
            vm.loadPlaces(type, lat, lng)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopbar(
            title = title,
            backButtonHandler = { onBackClick() }
        )
        Spacer(Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                permissionDenied -> {
                    // show explanation + open settings / retry
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text("Location permission denied. The app needs location to find nearby places.")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = {
                            // If user denied with "Don't ask again", open app settings
                            val shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                            if (!shouldShow) {
                                // open app settings
                                val intent = Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", activity.packageName, null)
                                )
                                activity.startActivity(intent)
                            } else {
                                // try asking again
                                permissionLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                    )
                                )
                            }
                        }) {
                            Text("Grant permission")
                        }
                    }
                }

                state is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.TopCenter))
                }

                state is UiState.Error -> {
                    val msg = (state as UiState.Error).message
                    Text(
                        text = "Error: $msg\n ${BuildConfig.GEOAPIFY_KEY}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state is UiState.Success -> {
                    val list = (state as UiState.Success<List<PlaceItem>>).data
                    if (list.isEmpty()) {
                        Text(
                            "No places found.",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(list) { place ->
                                PlaceCard (
                                    title = place.name,
                                    address = place.address,
                                    imageRes = imageRes ?: R.drawable.cafe,
                                )
                            }
                        }
                    }
                }

                else -> {
                    // waiting for location or initial state
                    Text(
                        "Waiting for location...",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

/**
 * Helper: request current location but guard the call with try/catch(SecurityException).
 * The callback is invoked when we get a location; lat/lng may be null if location unavailable.
 */
private fun requestLocationSafely(
    fused: FusedLocationProviderClient,
    onResult: (lat: Double?, lng: Double?) -> Unit
) {
    try {
        val cts = CancellationTokenSource()
        fused.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cts.token)
            .addOnSuccessListener { loc ->
                if (loc != null) {
                    onResult(loc.latitude, loc.longitude)
                } else {
                    onResult(null, null)
                }
            }
            .addOnFailureListener {
                onResult(null, null)
            }
    } catch (se: SecurityException) {
        // Permission was revoked between check and call — report nulls and let UI re-request permission
        onResult(null, null)
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesScreenPreview() {
    PlacesScreen(
        type = "cafe",
        title = "Cafes",
        imageRes = R.drawable.cafe,
        onPlaceClick = {},
        // hiltViewModel() cannot be used in a @Preview, so we'd need a fake/mock ViewModel
        // or we need to modify the original function to allow a nullable ViewModel.
    )
}
