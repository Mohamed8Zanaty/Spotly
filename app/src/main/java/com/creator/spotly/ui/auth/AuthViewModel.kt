package com.creator.spotly.ui.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creator.spotly.data.dto.UserHomeData
import com.creator.spotly.data.repository.AuthException
import com.creator.spotly.domain.repository.AuthRepository
import com.creator.spotly.domain.auth.PASSWORD_MIN_LENGTH
import com.creator.spotly.domain.model.User
import com.creator.spotly.ui.state.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _state = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val state: StateFlow<AuthUiState> = _state
    private val _uid = MutableStateFlow<String?>(firebaseAuth.currentUser?.uid)
    val uid: StateFlow<String?> = _uid.asStateFlow()


    fun signUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            // Validate input
            val validationError = validateSignUp(name, email, password, confirmPassword)
            if(validationError != null) {
                _state.value = AuthUiState.Error(validationError)
                return@launch
            }
            // Sign up
            _state.value = AuthUiState.Loading
            val result = repository.signUp(name, email, password)
            if(result.isSuccess) {
                val newUid = result.getOrNull()
                _state.value = AuthUiState.Success(newUid ?: "")
                _uid.value = newUid
            }
            else _state.value = AuthUiState.Error(result.exceptionOrNull()?.message ?: "Sign Up failed")
        }
    }

    private fun validateSignUp(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) : String? {
        if (name.isBlank()) return "Name cannot be empty"
        if (email.isBlank()) return "Email cannot be empty"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Invalid email format"
        if (password.length < 6) return "Password must be at least 6 characters"
        if (password != confirmPassword) return "Passwords do not match"

        return null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthUiState.Loading
            val result = repository.login(email, password)
            if(result.isSuccess) {
                val newUid = result.getOrNull()
                _state.value = AuthUiState.Success(newUid ?: "")
                _uid.value = newUid
            }
            else {
                val ex = result.exceptionOrNull()
                if (ex is AuthException)
                    _state.value = AuthUiState.Error(message = ex.message, fieldErrors = ex.fieldErrors)
                else
                    _state.value = AuthUiState.Error(message = ex?.localizedMessage ?: "Login failed", fieldErrors = FieldErrors(general = ex?.localizedMessage))
            }
        }
    }

    fun logOut() {
        repository.logOut()
        _uid.value = null
        _state.value = AuthUiState.Idle
    }

    fun nameErrorMessage(name: String) : String? {
        if (name.isBlank()) return "Name cannot be empty"
        return null
    }
    fun emailErrorMessage(email: String) : String? {
        if (email.isBlank()) return "Email cannot be empty"
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return "Invalid email format"
        return null

    }
    fun passwordErrorMessage(password: String) : String? {
        if (password.length < 6) return "Password must be at least 6 characters"
        return null
    }

    fun confirmPasswordErrorMessage(password: String, confirmPassword: String) : String? {
        if (password != confirmPassword) return "Passwords do not match"
        return null
    }
}

