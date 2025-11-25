package com.creator.spotly.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.api.Resource
import com.creator.spotly.api.geoapify.util.RemoteDataSource
import com.creator.spotly.data.dto.PlaceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    private val _searchState = MutableStateFlow<Resource<List<PlaceItem>>>(Resource.Success(emptyList()))
    val searchState = _searchState.asStateFlow()

    fun searchPlaces(query: String, lat: Double, lng: Double) {
        if (query.isBlank()) {
            _searchState.value = Resource.Success(emptyList())
            return
        }
        viewModelScope.launch {
            _searchState.value = Resource.Loading
            val result = remoteDataSource.searchPlacesByText(
                text = query,
                userLat = lat,
                userLng = lng
            )
            _searchState.value = result
        }
    }
}
