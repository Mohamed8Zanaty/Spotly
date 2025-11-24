package com.creator.spotly.api.geoapify.repository


import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.api.Resource
import com.creator.spotly.api.geoapify.model.details.PlaceDetailsUi
import com.creator.spotly.api.geoapify.util.RemoteDataSource
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource
) : PlacesRepository {
    override suspend fun getPlacesNearby(
        categories: String?,
        lat: Double,
        lng: Double,
        radiusMeters: Int,
        limit: Int
    ): Resource<List<PlaceItem>> {
        return remote.fetchPlacesByProximity(categories, lat, lng, radiusMeters, limit)
    }

    override suspend fun fetchPlaceDetails(id: String): Resource<PlaceDetailsUi> {
        return remote.fetchPlaceDetails(id)
    }
}