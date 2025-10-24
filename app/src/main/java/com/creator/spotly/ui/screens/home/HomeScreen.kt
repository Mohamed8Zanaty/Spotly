package com.creator.spotly.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.ui.screens.home.components.BestDestinationTitle
import com.creator.spotly.ui.screens.home.components.TitleSection
import com.creator.spotly.ui.screens.home.components.TopBarSection
import com.creator.spotly.ui.screens.home.components.TravelCardRow
import com.creator.spotly.ui.screens.home.components.TravelItem

val sample = List(5) { i ->
    TravelItem(
        id = "id_$i",
        imageUrl = "https://www.planetware.com/wpimages/2020/01/india-in-pictures-beautiful-places-to-photograph-taj-mahal.jpg",
        title = "Khai island beach",
        rating = "4.9",
        location = "Thailand",
        extraCount = 50
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onPlaceClick: (String) -> Unit = {},
    onNotificationsButtonClick: () -> Unit = {},
) {
    HomeContent(
        modifier = modifier,
        contentPadding = contentPadding,
        onPlaceClick = onPlaceClick,
        onNotificationsButtonClick = onNotificationsButtonClick
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onPlaceClick: (placeId: String) -> Unit = {},
    onNotificationsButtonClick: () -> Unit = {},
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 12.dp)
        ) {
            TopBarSection(
                notificationsIconHandler = onNotificationsButtonClick
            ) { }
            Spacer(Modifier.height(5.dp))
            TitleSection(
                "Discover the wonders of the ",
                "world!"
            )
            Spacer(Modifier.height(12.dp))
            BestDestinationTitle { }
            Spacer(Modifier.height(12.dp))
            TravelCardRow(sample, onItemClick = onPlaceClick)
        }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}