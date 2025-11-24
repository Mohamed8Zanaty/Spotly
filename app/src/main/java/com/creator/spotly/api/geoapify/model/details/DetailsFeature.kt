package com.creator.spotly.api.geoapify.model.details


import com.creator.spotly.api.geoapify.model.place.Geometry
import com.squareup.moshi.Json

data class DetailsFeature(
    @Json(name = "type") val type: String? = null,
    @Json(name = "properties") val properties: DetailProperties? = null,
    @Json(name = "geometry") val geometry: Geometry? = null
)
