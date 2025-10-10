package com.creator.spotly.ui.screens.messages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    onSearchQueryChanged: (String) -> Unit = {},
) {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
            onSearchQueryChanged(it)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon"
            )
        },
        placeholder = {
            Text("Search for chats & messages")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.75f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.75f)
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)



    )
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar()
}