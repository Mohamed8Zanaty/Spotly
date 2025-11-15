package com.creator.spotly.ui.auth

sealed interface AuthUiState {
    object Idle : AuthUiState
    object Loading : AuthUiState
    data class Success(val uid: String) : AuthUiState
    data class Error(val message: String? = null, val fieldErrors: FieldErrors? = null) : AuthUiState
}