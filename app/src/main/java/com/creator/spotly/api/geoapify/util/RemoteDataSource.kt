package com.creator.spotly.api.geoapify.util

import android.util.Log
import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.api.Resource
import com.creator.spotly.api.geoapify.model.details.PlaceDetailResponse
import com.creator.spotly.api.geoapify.model.details.PlaceDetailsUi
import com.creator.spotly.api.geoapify.model.place.PlacesResponse
import com.creator.spotly.api.geoapify.service.GeoapifyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: GeoapifyService
) {

    suspend fun searchPlacesByText(
        text: String,
        userLat: Double?,
        userLng: Double?,
        limit: Int = 20,
        lang: String? = null
    ): Resource<List<PlaceItem>> = withContext(Dispatchers.IO) {
        try {
            var bias: String? = null
            if (userLat != null && userLng != null) {
                if (userLat.isFinite() && userLng.isFinite() && userLat in -90.0..90.0 && userLng in -180.0..180.0) {
                    bias = String.format(Locale.US, "proximity:%f,%f", userLng, userLat)
                }
            }

            Log.d(
                "GeoDebug",
                "Calling searchPlaces text=$text bias=$bias limit=$limit"
            )

            val resp: PlacesResponse = service.searchPlaces(
                text = text,
                bias = bias,
                limit = limit,
                lang = lang
            )

            val items = resp.features.mapNotNull { it.toPlaceItem() }
            Resource.Success(items)
        } catch (ioEx: IOException) {
            Log.e("GeoHttp", "Network error: ${ioEx.message}", ioEx)
            Resource.Error("Network error: ${ioEx.message}", ioEx)
        } catch (httpEx: HttpException) {
            val code = httpEx.code()
            val errBody = try {
                httpEx.response()?.errorBody()?.string()
            } catch (e: Exception) {
                null
            }
            Log.e("GeoHttp", "HTTP $code -> $errBody", httpEx)
            val message = buildString {
                append("Server error $code")
                if (!errBody.isNullOrBlank()) {
                    append(": $errBody")
                }
            }
            Resource.Error(message, httpEx)
        } catch (ex: Exception) {
            Log.e("GeoHttp", "Unexpected error: ${ex.message}", ex)
            Resource.Error("Unexpected error: ${ex.message}", ex)
        }
    }

    suspend fun fetchPlacesByProximity(
        categories: String?,
        userLat: Double,
        userLng: Double,
        radiusMeters: Int = 1000,
        limit: Int = 20,
        lang: String? = null
    ): Resource<List<PlaceItem>> = withContext(Dispatchers.IO) {
        try {
            if (!userLat.isFinite() || !userLng.isFinite()) {
                return@withContext Resource.Error("Invalid coordinates")
            }
            if (radiusMeters <= 0) {
                return@withContext Resource.Error("radiusMeters must be > 0")
            }
            if (userLat !in -90.0..90.0 || userLng !in -180.0..180.0) {
                return@withContext Resource.Error("Coordinates out of range")
            }

            val filter = String.format(
                Locale.US,
                "circle:%f,%f,%d",
                userLng,
                userLat,
                radiusMeters
            )
            val bias = String.format(Locale.US, "proximity:%f,%f", userLng, userLat)

            Log.d(
                "GeoDebug",
                "Calling getPlaces categories=$categories filter=$filter bias=$bias limit=$limit"
            )

            val resp: PlacesResponse = service.getPlaces(
                categories = categories,
                filter = filter,
                bias = bias,
                limit = limit,
                lang = lang
            )

            val items = resp.features.mapNotNull { it.toPlaceItem() }
            Resource.Success(items)
        } catch (ioEx: IOException) {
            Log.e("GeoHttp", "Network error: ${ioEx.message}", ioEx)
            Resource.Error("Network error: ${ioEx.message}", ioEx)
        } catch (httpEx: HttpException) {
            val code = httpEx.code()
            val errBody = try {
                httpEx.response()?.errorBody()?.string()
            } catch (e: Exception) {
                null
            }
            Log.e("GeoHttp", "HTTP $code -> $errBody", httpEx)
            val message = buildString {
                append("Server error $code")
                if (!errBody.isNullOrBlank()) {
                    append(": $errBody")
                }
            }
            Resource.Error(message, httpEx)
        } catch (ex: Exception) {
            Log.e("GeoHttp", "Unexpected error: ${ex.message}", ex)
            Resource.Error("Unexpected error: ${ex.message}", ex)
        }
    }
    suspend fun fetchPlaceDetails(placeId: String, lang: String? = null): Resource<PlaceDetailsUi> =
        withContext(Dispatchers.IO) {
            try {
                val resp: PlaceDetailResponse =
                    service.getPlaceDetails(placeId = placeId, lang = lang)
                val details = resp.toPlaceDetailsUI()
                if (details != null) {
                    Resource.Success(details)
                } else {
                    Resource.Error("No details available for place $placeId")
                }
            } catch (ioEx: IOException) {
                Resource.Error("Network error: ${ioEx.message}", ioEx)
            } catch (httpEx: HttpException) {
                Resource.Error("Server error: ${httpEx.message()}", httpEx)
            } catch (ex: Exception) {
                Resource.Error("Unexpected error: ${ex.message}", ex)
            }
        }
}