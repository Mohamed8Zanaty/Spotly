package com.creator.spotly.domain.model



import com.google.firebase.firestore.GeoPoint
import java.io.Serializable


data class Place(
    var id: String = "",
    var name: String = "",
    var address: String = "",
    var location: GeoPoint? = null,
    var imageUrl: String = "",
) : Serializable
