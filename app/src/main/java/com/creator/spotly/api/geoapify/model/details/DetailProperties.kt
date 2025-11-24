package com.creator.spotly.api.geoapify.model.details

import com.squareup.moshi.Json

data class DetailProperties(
    @Json(name = "place_id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "address_line2") val addressLine2: String? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "website") val website: String? = null,

    // opening_hours is sometimes a nested object — map the common weekday_text field
    @Json(name = "opening_hours") val openingHours: OpeningHours? = null,

    // rating & user_ratings_total may or may not be present depending on data provider
    @Json(name = "rating") val rating: Double? = null,
    @Json(name = "user_ratings_total") val userRatingsTotal: Int? = null,

    // reviews are provider-dependent; try to parse common structure
    @Json(name = "reviews") val reviews: List<Review>? = null
)
