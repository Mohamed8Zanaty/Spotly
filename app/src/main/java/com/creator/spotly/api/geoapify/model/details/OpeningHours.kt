package com.creator.spotly.api.geoapify.model.details

import com.squareup.moshi.Json

data class OpeningHours(
    @Json(name = "weekday_text") val weekdayText: List<String>? = null,
)
