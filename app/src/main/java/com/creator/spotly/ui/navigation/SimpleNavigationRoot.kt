package com.creator.spotly.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.creator.spotly.auth.viewmodel.AuthViewModel
import com.creator.spotly.auth.ui.signup.SignUpScreen

@Composable
fun SimpleNavigationRoot() {
    val authViewModel: AuthViewModel = hiltViewModel()
    val uid by authViewModel.uid.collectAsState()

    if (uid == null) {
        // Show signup screen for testing
        SignUpScreen(
            onSignUpSuccess = {
                // Handle success
            }
        )
    } else {
        // Show home screen
        // HomeScreen()
    }
}