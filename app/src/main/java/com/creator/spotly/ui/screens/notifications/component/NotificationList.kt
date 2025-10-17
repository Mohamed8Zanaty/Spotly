package com.creator.spotly.ui.screens.notifications.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.creator.spotly.ui.screens.notifications.NotificationData


@Composable
fun NotificationList(notifications: List<NotificationData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notifications) { item ->
            NotificationItem(data = item, onClick = {})
            Divider(color = Color(0xFFF2F2F2))
        }
    }
}

