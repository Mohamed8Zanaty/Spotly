package com.creator.spotly.data.dto

import java.io.Serializable

data class UserProfileData(
    val uid: String,
    val name: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val rewardPoints: Int = 0,
    val travelTrips: Int = 0,
    val bucketListItems: Int = 0

) : Serializable
