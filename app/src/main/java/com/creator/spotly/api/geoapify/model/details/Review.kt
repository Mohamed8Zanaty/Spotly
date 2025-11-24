package com.creator.spotly.api.geoapify.model.details

import com.squareup.moshi.Json

data class Review(
    @Json(name = "author_name") val authorName: String? = null,
    @Json(name = "text") val text: String? = null,
    @Json(name = "rating") val rating: Double? = null
)
