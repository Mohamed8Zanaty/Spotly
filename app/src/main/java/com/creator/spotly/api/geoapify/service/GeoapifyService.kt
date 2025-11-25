package com.creator.spotly.api.geoapify.service


import com.creator.spotly.api.geoapify.model.details.PlaceDetailResponse
import com.creator.spotly.api.geoapify.model.place.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoapifyService {
    @GET("v2/places")
    suspend fun getPlaces(
        @Query("categories") categories: String? = null,
        @Query("filter") filter: String? = null,
        @Query("bias") bias: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("lang") lang: String? = null
    ): PlacesResponse

    @GET("v1/geocode/search")
    suspend fun searchPlaces(
        @Query("text") text: String,
        @Query("filter") filter: String? = null,
        @Query("bias") bias: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("lang") lang: String? = null
    ): PlacesResponse

    @GET("v2/place-details")
    suspend fun getPlaceDetails(
        @Query("place_id") placeId: String,
        @Query("features") features: String? = null, // optional features param
        @Query("lang") lang: String? = null
    ): PlaceDetailResponse
}