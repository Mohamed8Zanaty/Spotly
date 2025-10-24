package com.creator.spotly.ui.screens.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.ui.screens.home.HomeContent
import com.creator.spotly.ui.screens.messages.components.ChatList
import com.creator.spotly.ui.screens.messages.components.SearchBar
import com.creator.spotly.ui.screens.messages.components.Title
@Composable
fun MessagesScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onOpenChat: (chatId: String) -> Unit = {},
) {
    MessagesContent {  }
}

@Composable
fun MessagesContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onOpenChat: (chatId: String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Title()
        Spacer(Modifier.height(16.dp))
        SearchBar { }
        Spacer(Modifier.height(16.dp))
        ChatList()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MessagesScreenPreview() {
    MessagesScreen()
}