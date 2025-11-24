package com.creator.spotly.api.geoapify.model.details

data class PlaceDetailsUi(
    val id: String,
    val name: String,
    val address: String?,
    val phone: String?,
    val website: String?,
    val openingHours: List<String>?,
    val rating: Double?,
    val userRatingsTotal: Int?,
    val reviews: List<String>?,
)
