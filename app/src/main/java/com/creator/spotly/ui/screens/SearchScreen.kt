package com.creator.spotly.ui.screens

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
import com.creator.spotly.ui.theme.SpotlyTheme


@Composable
fun SearchScreen(
    

){

    Column(
        modifier = Modifier.padding(16.dp),
       // verticalArrangement = Arrangement.spacedBy(8.dp) // space between items
    ) {
        AppbarSearch()
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
    SpotlyTheme  {
        SearchScreen()
    }
}