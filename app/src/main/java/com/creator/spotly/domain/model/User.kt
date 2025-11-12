package com.creator.spotly.domain.model

import java.io.Serializable

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var createdAt: Any? = null,
    var favoritePlaces : MutableList<Place> = mutableListOf()
) : Serializable

