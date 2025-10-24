package com.creator.spotly.ui.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(Color(0xFFF7F7F9), shape = CircleShape)
                .padding(8.dp)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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

@Preview
@Composable
private fun TopBarPreview() {
    TopBar()
}