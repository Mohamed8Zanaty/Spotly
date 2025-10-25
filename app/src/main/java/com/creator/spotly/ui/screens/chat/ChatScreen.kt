package com.creator.spotly.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.domain.model.Message
import com.creator.spotly.ui.screens.chat.components.ChatInputBar
import com.creator.spotly.ui.screens.chat.components.MessageBubble
import com.creator.spotly.ui.screens.chat.components.TopBar


@Composable
fun ChatScreen(

) {
    val messages = listOf(
        Message("Hello!", "9:24", true, isSeen = true),
        Message(
            "Thank you very much for your traveling, we really like the apartments. we will stay here for another 5 days...",
            "9:30",
            true
        ),
        Message("Hello!", "9:34", false, true),
        Message("I’m very glad you like it👍", "9:35", false),
        Message("We are arriving today at 01:45, will someone be at home?", "9:37", false, true),
        Message("I will be at home", "9:39", true, isSeen = true)
    )
    var message by remember { mutableStateOf("") }
    ChatContent(
        messages,
        message,
        onSendMessage = { },
        onVoiceMessage = { },
        onAttachFile = { },
        onMessageChange = { message = it },
        clearMessage = { message = "" }
    )

}

@Composable
fun ChatContent(
    messages: List<Message>,
    message: String,
    onSendMessage: (String) -> Unit = { },
    onVoiceMessage: () -> Unit = { },
    onAttachFile: () -> Unit = { },
    onMessageChange: (String) -> Unit = { },
    clearMessage: () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F9))
    ) {
        TopBar()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Today",
                color = Color.Gray,
                fontSize = 13.sp,
                modifier = Modifier
                    .background(Color(0xFFEFEFF1), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { msg ->
                MessageBubble(msg)
            }
        }

        ChatInputBar(
            message = message,
            onSendMessage = onSendMessage,
            onVoiceMessage = onVoiceMessage,
            onAttachFile = onAttachFile,
            onMessageChange = onMessageChange,
            clearMessage = clearMessage
        )
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
