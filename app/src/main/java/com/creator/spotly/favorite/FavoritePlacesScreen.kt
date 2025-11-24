package com.creator.spotly.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.data.dto.PlaceItem

import com.creator.spotly.favorite.component.FavoritePlacesGrid

@Composable
fun FavoritePlacesScreen(places: List<PlaceItem>, onBackClick: () -> Unit) {
    FavoritePlacesContent(places = places, onBackClick = onBackClick)
}
@Composable
fun FavoritePlacesContent(
    places : List<PlaceItem>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite Places",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(16.dp))

        FavoritePlacesGrid(places)
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun FavoritePlacesPreview() {
//    val samplePlaces =
//        listOf(
//            Place("Niladri Reservoir", "Tekergat, Sunamgnj", "https://picsum.photos/300/200?1"),
//            Place("Casa Las Tirtugas", "Av Damero, Mexico", "https://picsum.photos/300/200?2"),
//            Place("Aonang Villa Resort", "Bastola, Islumpur", "https://picsum.photos/300/200?3"),
//            Place("Rangauti Resort", "Sylhet, Airport Road", "https://picsum.photos/300/200?4"),
//            Place("Kachura Resort", "Vellima, Island", "https://picsum.photos/300/200?5"),
//            Place("Shakardu Resort", "Shakartu, Pakistan", "https://picsum.photos/300/200?6"),
//        )
//    FavoritePlacesScreen(places = samplePlaces, onBackClick = {})
//}onBackClick