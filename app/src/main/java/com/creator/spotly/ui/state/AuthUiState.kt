package com.creator.spotly.ui.state

import com.creator.spotly.ui.auth.FieldErrors

sealed interface AuthUiState {
    object Idle : AuthUiState
    object Loading : AuthUiState
    data class Success(val uid: String) : AuthUiState
    data class Error(val message: String? = null, val fieldErrors: FieldErrors? = null) : AuthUiState
}