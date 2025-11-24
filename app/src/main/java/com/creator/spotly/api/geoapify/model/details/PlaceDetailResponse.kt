package com.creator.spotly.api.geoapify.model.details


import com.squareup.moshi.Json

data class PlaceDetailResponse(
    @Json(name = "type") val type: String? = null,
    @Json(name = "features") val features: List<DetailsFeature> = emptyList()
)
