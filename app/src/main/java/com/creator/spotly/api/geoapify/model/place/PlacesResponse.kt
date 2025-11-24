package com.creator.spotly.api.geoapify.model.place

import com.squareup.moshi.Json

data class PlacesResponse(
    @Json(name = "type") val type: String? = null,
    @Json(name = "features") val features: List<Feature> = emptyList()
)
