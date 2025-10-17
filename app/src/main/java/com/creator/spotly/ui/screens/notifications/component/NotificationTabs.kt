package com.creator.spotly.ui.screens.notifications.component

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationTabs() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Recent", "Earlier", "Archived")

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.White,
        contentColor = Color(0xFFFF5B00)
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = {
                    Text(
                        text = title,
                        color = if (selectedTab == index) Color(0xFFFF5B00) else Color.Gray,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun NotificationTabsPreview() {
    NotificationTabs()
}
