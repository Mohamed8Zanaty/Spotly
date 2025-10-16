package com.example.spotlychatscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spotlychatscreen.ui.theme.SpotlyChatScreenTheme
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment


data class Message(
    val text: String,
    val time: String,
    val isFromUser: Boolean,
    val showAvatar: Boolean = false,
    val isSeen: Boolean = false
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotlyChatScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MessagesSection()
                }
            }
        }
    }
}

@Composable
fun TopBar(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(16.dp)
        , verticalAlignment = Alignment.Top
        , horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(Color(0xFFF7F7F9), shape = CircleShape)
                .padding(8.dp)

        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back to Messeges"
            )
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ahmed anjims",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Active Now",
                color = Color.Green,
                fontWeight = FontWeight.Light
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .background(Color(0xFFF7F7F9), shape = CircleShape)
                .padding(8.dp)

        ) {
            Icon(
                imageVector = Icons.Outlined.Call,
                contentDescription = "Call"
            )
        }

    }
}
@Composable
fun MessagesSection() {
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

        ChatInputBar()
    }
}



@Composable
fun MessageBubble(message: Message) {
    val bubbleColor = if (message.isFromUser) Color(0xFFE6F2FF) else Color.White
    val alignment = if (message.isFromUser) Arrangement.End else Arrangement.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = alignment
    ) {
        if (!message.isFromUser && message.showAvatar) {
            Image(
                painter = painterResource(id = R.drawable.av1),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .padding(end = 6.dp),
                contentScale = ContentScale.Crop
            )
        } else if (!message.isFromUser) {
            Spacer(modifier = Modifier.width(42.dp))
        }

        Box(
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(max = 280.dp)
                .background(bubbleColor, RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Column(horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start) {
                Text(
                    text = message.text,
                    fontSize = 15.sp,
                    color = Color.Black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = message.time,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    if (message.isFromUser) {
                        if (message.isSeen) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy((-4).dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Seen",
                                    tint = Color(0xFF00C853),
                                    modifier = Modifier.size(14.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color(0xFF00C853),
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        } else {

                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Sent",
                                tint = Color.Gray,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        if (message.isFromUser) {
            Spacer(modifier = Modifier.width(42.dp))
        }
    }
}


@Composable
fun ChatInputBar() {

    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .navigationBarsPadding()
            .imePadding(),
        verticalAlignment = Alignment.Bottom
    ) {

        TextField(
            value = message,
            onValueChange = { message = it },
            placeholder = {
                Text("Type your message",
                color = Color.Gray) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachFile,
                    contentDescription = "Attach",
                    tint = Color.Gray
                )
            },
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF7F7F9),
                unfocusedContainerColor = Color(0xFFF7F7F9),
                cursorColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
        )

        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(start = 8.dp)
                .size(52.dp)
                .background(Color(0xFFFF6A00), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice Message",
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview(){
        MessagesSection()
}
