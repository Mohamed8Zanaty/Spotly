package com.creator.spotly.data.dto

import java.io.Serializable


data class UserHomeData(
    val uid: String,
    val name: String? = null,
    val avatar: String? = null,
) : Serializable


