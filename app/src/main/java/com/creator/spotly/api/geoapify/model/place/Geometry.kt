package com.creator.spotly.api.geoapify.model.place

import com.squareup.moshi.Json

data class Geometry(
    @Json(name = "coordinates")
    val coordinates: List<Double>
)
