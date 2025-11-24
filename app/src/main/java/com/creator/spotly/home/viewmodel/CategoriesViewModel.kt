package com.creator.spotly.home.viewmodel


import androidx.lifecycle.ViewModel
import com.creator.spotly.R
import com.creator.spotly.domain.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(): ViewModel() {
    val categories = listOf(
        Category("catering.restaurant", "Restaurants", R.drawable.restaurant),
        Category("activity.sport_club", "Gyms", R.drawable.gym),
        Category("catering.cafe", "Cafes", R.drawable.cafe),
        Category("entertainment.museum", "Museums", R.drawable.museum),
        Category("entertainment.zoo", "Zoos", R.drawable.zoo),
        Category("entertainment.cinema", "Cinemas", R.drawable.cinema),
//        Category("commercial.hotel", "Hotels", R.drawable.hotel),
        Category("commercial.shopping_mall", "Shopping Malls", R.drawable.mall),
    )

}