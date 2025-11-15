package com.creator.spotly.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.domain.model.User
import com.creator.spotly.ui.auth.AuthViewModel
import com.creator.spotly.ui.home.components.BestDestinationTitle
import com.creator.spotly.ui.home.components.TitleSection
import com.creator.spotly.ui.home.components.TopBarSection
import com.creator.spotly.ui.home.components.TravelCardRow
import com.creator.spotly.ui.home.components.TravelItem

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
    homeViewModel: HomeViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onPlaceClick: (String) -> Unit = {},
    onProfileButtonClick: () -> Unit = {},

    ) {
    val userHomeData by homeViewModel.userHomeData.collectAsState()
    val displayName = userHomeData?.name ?: "User"
    val avatarUrl = userHomeData?.avatar

    HomeContent(
        modifier = modifier,
        contentPadding = contentPadding,
        onPlaceClick = onPlaceClick,
        onProfileButtonClick = onProfileButtonClick,
        name = displayName
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onPlaceClick: (placeId: String) -> Unit = {},
    onProfileButtonClick: () -> Unit = {},
    name : String? = null,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 12.dp)
        ) {
            TopBarSection(
                profileButtonHandler = onProfileButtonClick,
                name = name ?: "User"
            )
            Spacer(Modifier.height(5.dp))
            TitleSection(
                text1 = "Discover the wonders of the ",
                text2 = "world!"
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