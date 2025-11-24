package com.creator.spotly.placeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.data.dto.PlaceItem
import com.creator.spotly.api.Resource
import com.creator.spotly.api.geoapify.repository.PlacesRepository
import com.creator.spotly.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesListViewModel @Inject constructor(
    private val repository: PlacesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<PlaceItem>>>(UiState.Loading)
    val state: StateFlow<UiState<List<PlaceItem>>> = _state.asStateFlow()

    fun loadPlaces(categories: String?, lat: Double, lng: Double, radiusMeters: Int = 100000) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            when (val res = repository.getPlacesNearby(categories, lat, lng, radiusMeters)) {
                is Resource.Success -> _state.value = UiState.Success(res.data)
                is Resource.Error -> _state.value = UiState.Error(res.message)
                is Resource.Loading -> _state.value = UiState.Loading
            }
        }
    }
}