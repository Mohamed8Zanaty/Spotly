package com.creator.spotly.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val userHomeData: StateFlow<UserHomeData?> =
        userRepository.currentUserHomeDataFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}