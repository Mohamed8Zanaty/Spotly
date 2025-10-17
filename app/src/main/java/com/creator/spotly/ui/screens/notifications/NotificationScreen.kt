package com.creator.spotly.ui.screens.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.creator.spotly.ui.screens.notifications.component.NotificationList
import com.creator.spotly.ui.screens.notifications.component.NotificationTabs
import com.creator.spotly.ui.screens.notifications.component.NotificationTopBar

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    val notifications = listOf(
        NotificationData(
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm6rA9kd9jV8ZeNIchdz0ClkdTOob8dE9jWA&s",
            title = "Super Offer",
            description = "Get 60% off in our first booking",
            time = "Sun, 12:40pm"
        ),
        NotificationData(
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm6rA9kd9jV8ZeNIchdz0ClkdTOob8dE9jWA&s",
            title = "Super Offer",
            description = "Get 60% off in our first booking",
            time = "Mon, 11:50pm"
        ),
        NotificationData(
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm6rA9kd9jV8ZeNIchdz0ClkdTOob8dE9jWA&s",
            title = "Super Offer",
            description = "Get 60% off in our first booking",
            time = "Tue, 10:56pm"
        )
    )

    Column(modifier = Modifier.fillMaxSize().then(modifier)) {
        NotificationTopBar(
            onBack = {}, onClearAll = {},
            modifier = Modifier
        )
        NotificationTabs()
        NotificationList(notifications = notifications)
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}