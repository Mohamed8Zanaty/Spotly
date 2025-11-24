package com.creator.spotly.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlaceItem(
    val id: String,
    val name: String,
    val address: String?,
    val lat: Double?,
    val lng: Double?,
)
