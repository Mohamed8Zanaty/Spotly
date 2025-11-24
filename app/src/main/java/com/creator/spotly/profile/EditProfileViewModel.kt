package com.creator.spotly.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.data.dto.UserEditProfileData
import com.creator.spotly.auth.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EditProfileUiState(
    val name: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState

    init {
        loadCurrentName()
    }

    private fun loadCurrentName() {
        viewModelScope.launch {
            userRepository.currentUserProfileFlow().collect { profile ->
                val currentName = profile?.name ?: ""
                _uiState.value = _uiState.value.copy(name = currentName)
            }
        }
    }

    // Called when user edits the name
    fun onNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName)
    }

    // Save the new name to Firestore
    fun saveProfile() {
        val uid = userRepository.getCurrentUid() ?: return
        val name = _uiState.value.name.trim()
        if (name.isEmpty()) {
            _uiState.value = _uiState.value.copy(error = "Name cannot be empty")
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                val data = UserEditProfileData(name = name)
                userRepository.updateUserProfile(uid, data)
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to update name"
                )
            }
        }
    }
}
