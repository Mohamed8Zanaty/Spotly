package com.creator.spotly.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.search.components.AppbarSearch
import com.creator.spotly.search.components.ItemSearch
import com.creator.spotly.search.components.SearchBar
import com.creator.spotly.ui.theme.SpotlyTheme


@Composable
fun SearchScreen(
    onBack: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    SearchContent(
        onBack = onBack,
        onCancel = onCancel
    )
}

@Composable
fun SearchContent(
        onBack: () -> Unit = {},
        onCancel: () -> Unit = {}
    ) {
    Column(
        modifier = Modifier.padding(16.dp),
        // verticalArrangement = Arrangement.spacedBy(8.dp) // space between items
    ) {
        AppbarSearch(
            backButtonHandler = onBack,
            cancelHandler = onCancel
        )
        Spacer(modifier = Modifier.height(15.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Search Places", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))
        ItemSearch()

    }
    
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    SpotlyTheme {
        SearchScreen()
    }
}