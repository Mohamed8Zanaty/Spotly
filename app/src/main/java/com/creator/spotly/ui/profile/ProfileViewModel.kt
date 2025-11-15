package com.creator.spotly.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.data.dto.UserProfileData
import com.creator.spotly.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val profile: StateFlow<UserProfileData?> =
        userRepository.currentUserProfileFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}