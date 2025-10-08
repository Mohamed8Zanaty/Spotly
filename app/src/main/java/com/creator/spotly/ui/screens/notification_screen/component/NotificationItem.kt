package com.creator.spotly.ui.screens.notification_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.creator.spotly.ui.screens.notification_screen.NotificationData

@Composable
fun NotificationItem(modifier: Modifier = Modifier, data: NotificationData, onClick: () -> Unit) {
  ListItem(
      modifier = Modifier.clickable() { onClick }.then(modifier),
      leadingContent = {
        AsyncImage(
            model = data.imageUrl,
            contentDescription = data.title,
            modifier = Modifier.size(48.dp).clip(CircleShape),
        )
      },
      headlineContent = { Text(text = data.title) },
      supportingContent = { Text(text = data.description) },
      trailingContent = { Text(text = data.time) },
  )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationItemPreview() {
  val data =
      NotificationData(
          imageUrl =
              "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm6rA9kd9jV8ZeNIchdz0ClkdTOob8dE9jWA&s",
          title = "New Like",
          description = "Someone liked your photo.",
          time = "5m ago",
      )
  NotificationItem(data = data, onClick = {})
}
