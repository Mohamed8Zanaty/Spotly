package com.creator.spotly.ui.screens.favorite.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.creator.spotly.ui.components.CustomTopbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar(backButtonHandler: () -> Unit) {
    CustomTopbar(
        backButtonHandler = backButtonHandler,
        title = "Favorite Places"
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoriteTopBarPreview() {
    FavoriteTopBar { }
}