package com.creator.spotly.ui.screens.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.domain.model.Place

@Composable
fun FavoritePlacesGrid(places: List<Place>) {
  LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.fillMaxSize().padding(12.dp),
  ) {
    items(places) { place -> FavoritePlaceCard(place) }
  }
}

@Preview(showSystemUi = true)
@Composable
private fun FavoritePlacesGridPreview() {
    val places = List(5) {
        Place(
            name = "Zanaty",
            location = "El-marg",
            imageUrl = "blabla"
        )
    }

    FavoritePlacesGrid(places)
}
