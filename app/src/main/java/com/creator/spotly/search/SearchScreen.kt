package com.creator.spotly.search

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.creator.spotly.api.Resource
import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.search.components.ItemsSearch
import com.creator.spotly.search.components.SearchBar
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchState by viewModel.searchState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<android.location.Location?>(null) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                userLocation = location
                location?.let {
                    Log.d("SearchScreen", "User location: ${it.latitude}, ${it.longitude}")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    userLocation?.let { location ->
                        viewModel.searchPlaces(it, location.latitude, location.longitude)
                    }
                }
            )
            SearchContent(searchState = searchState, searchQuery = searchQuery)
        }
    }
}

@Composable
fun SearchContent(searchState: Resource<List<PlaceItem>>, searchQuery: String) {
    when (searchState) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            if (searchState.data.isNullOrEmpty()) {
                if (searchQuery.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No places found")
                    }
                }
            } else {
                ItemsSearch(
                    places = searchState.data
                )
            }
        }

        is Resource.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = searchState.message ?: "An unknown error occurred")
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}
