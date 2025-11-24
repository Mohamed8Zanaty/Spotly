package com.creator.spotly.home.ui

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
import com.creator.spotly.domain.model.Category
import com.creator.spotly.home.ui.components.BestDestinationTitle
import com.creator.spotly.home.ui.components.Categories
import com.creator.spotly.home.ui.components.TitleSection
import com.creator.spotly.home.ui.components.TopBarSection

import com.creator.spotly.home.viewmodel.CategoriesViewModel
import com.creator.spotly.home.viewmodel.HomeViewModel



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onCategoryClick: (Category) -> Unit = {},
    onProfileButtonClick: () -> Unit = {},

    ) {
    val userHomeData by homeViewModel.userHomeData.collectAsState()
    val displayName = userHomeData?.name ?: "User"
    val avatarUrl = userHomeData?.avatar

    HomeContent(
        modifier = modifier,
        contentPadding = contentPadding,
        onCategoryClick = onCategoryClick,
        onProfileButtonClick = onProfileButtonClick,
        name = displayName,
        categories = categoriesViewModel.categories
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onCategoryClick: (Category) -> Unit = {},
    onProfileButtonClick: () -> Unit = {},
    name : String? = null,
    categories : List<Category> = emptyList()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            TopBarSection(
                profileButtonHandler = onProfileButtonClick,
                name = name ?: "User"
            )
            Spacer(Modifier.height(12.dp))
            TitleSection(
                text1 = "Discover the wonders of the ",
                text2 = "world!"
            )
            Spacer(Modifier.height(24.dp))
            BestDestinationTitle()
            Spacer(Modifier.height(16.dp))
            Categories(categories, onSelect = onCategoryClick)
        }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}