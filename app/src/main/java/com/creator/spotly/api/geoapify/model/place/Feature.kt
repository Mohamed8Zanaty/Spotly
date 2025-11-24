package com.creator.spotly.api.geoapify.model.place


import com.squareup.moshi.Json

data class Feature(
    @Json(name = "type") val type: String? = null,
    @Json(name = "properties") val properties: Properties? = null,
    @Json(name = "geometry") val geometry: Geometry? = null,
    val latitude: Double? = geometry?.coordinates?.getOrNull(1),
    val longitude: Double? = geometry?.coordinates?.getOrNull(0)
)
