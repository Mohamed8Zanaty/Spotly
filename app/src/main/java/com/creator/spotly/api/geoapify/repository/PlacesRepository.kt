package com.creator.spotly.api.geoapify.repository

import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.api.Resource
import com.creator.spotly.api.geoapify.model.details.PlaceDetailsUi

interface PlacesRepository {
    suspend fun getPlacesNearby(
        categories: String?,
        lat: Double,
        lng: Double,
        radiusMeters: Int = 1000,
        limit: Int = 20
    ): Resource<List<PlaceItem>>

    suspend fun fetchPlaceDetails(placeId: String): Resource<PlaceDetailsUi>
}