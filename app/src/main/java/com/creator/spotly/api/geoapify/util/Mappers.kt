package com.creator.spotly.api.geoapify.util

import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.api.geoapify.model.details.DetailProperties
import com.creator.spotly.api.geoapify.model.details.DetailsFeature
import com.creator.spotly.api.geoapify.model.details.PlaceDetailResponse
import com.creator.spotly.api.geoapify.model.details.PlaceDetailsUi
import com.creator.spotly.api.geoapify.model.place.Feature

fun Feature.toPlaceItem(): PlaceItem? {
    val pid = this.properties?.id ?: return null
    val name = this.properties.name ?: "Unknown"
    val addr = this.properties.address
    val lat = this.latitude
    val lng = this.longitude
    return PlaceItem(
        id = pid,
        name = name,
        address = addr,
        lat = lat,
        lng = lng,
    )
}

fun PlaceDetailResponse.toPlaceDetailsUI(): PlaceDetailsUi? {
    val first: DetailsFeature = this.features.firstOrNull() ?: return null
    val props: DetailProperties? = first.properties

    if (props == null) return null

    val opening: List<String>? = props.openingHours?.weekdayText?.takeIf { it.isNotEmpty() }

    // map reviews to simple strings (or keep structured if you prefer)
    val reviews: List<String>? = props.reviews?.mapNotNull { r ->
        // prefer author + text, fallback to text
        when {
            !r?.authorName.isNullOrBlank() && !r.text.isNullOrBlank() -> "${r.authorName}: ${r.text}"
            !r?.text.isNullOrBlank() -> r.text
            else -> null
        }
    }?.takeIf { it.isNotEmpty() }

    return PlaceDetailsUi(
        id = props.id ?: "",
        name = props.name ?: "Unknown",
        address = props.addressLine2,
        phone = props.phone,
        website = props.website,
        openingHours = opening,
        rating = props.rating,
        userRatingsTotal = props.userRatingsTotal,
        reviews = reviews
    )
}