package com.creator.spotly.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creator.spotly.data.dto.PlaceItem


@Composable
fun FavoritePlacesGrid(places: List<PlaceItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        items(places) { place -> FavoritePlaceCard(place) }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun FavoritePlacesGridPreview() {
//    val places = List(5) {
//        Place(
//            name = "Zanaty",
//            address = "El-marg",
//            imageUrl = "blabla"
//        )
//    }
//
//    FavoritePlacesGrid(places)
//}
