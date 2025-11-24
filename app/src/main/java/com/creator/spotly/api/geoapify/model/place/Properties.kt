package com.creator.spotly.api.geoapify.model.place

import com.squareup.moshi.Json

data class Properties(
    @Json(name = "place_id") val id: String,
    @Json(name = "name") val name: String?,
    @Json(name = "address_line2") val address: String?
)
