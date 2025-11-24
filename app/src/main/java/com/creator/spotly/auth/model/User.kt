package com.creator.spotly.auth.model

import com.creator.spotly.data.dto.PlaceItem
import java.io.Serializable

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var createdAt: Any? = null,
    var avatar: String = "",
    var favoritePlaces : MutableList<PlaceItem> = mutableListOf()
) : Serializable