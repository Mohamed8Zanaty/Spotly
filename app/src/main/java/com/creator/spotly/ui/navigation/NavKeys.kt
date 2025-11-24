package com.creator.spotly.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.creator.spotly.data.dto.PlaceItem
import kotlinx.serialization.Serializable

@Serializable
data object WelcomeScreen : NavKey
@Serializable
data object SignUpScreen : NavKey
@Serializable
data object LoginScreen : NavKey
//@Serializable
//data object PopularPlacesScreen : NavKey
//@Serializable
//data object NotificationScreen : NavKey
@Serializable
data object ProfileScreen : NavKey
@Serializable
data object EditProfileScreen : NavKey
//@Serializable
//data object MessagesScreen : NavKey
//@Serializable
//data object ChatScreen : NavKey
//@Serializable
//data object FavoritesScreen : NavKey
@Serializable
data object HomeScreen : NavKey
@Serializable
data object SearchScreen : NavKey
@Serializable
data class PlacesScreen(
    val type: String,
    val title: String,
    val imageRes: Int? = null
) : NavKey
