package com.creator.spotly

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.creator.spotly.ui.state.AuthUiState
import com.creator.spotly.auth.viewmodel.AuthViewModel
import com.creator.spotly.ui.components.Curve
import com.creator.spotly.ui.components.CustomTextField
import com.creator.spotly.ui.components.ValidateErrorText

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val isLoading = uiState is AuthUiState.Loading
    var firstTime by remember { mutableStateOf(true) }
    var authEmailErrorMessage by remember { mutableStateOf<String?>(null) }
    var authPasswordErrorMessage by remember { mutableStateOf<String?>(null) }
    var authErrorMessage by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                authEmailErrorMessage = ""
                authPasswordErrorMessage = ""
                onLoginSuccess((uiState as AuthUiState.Success).uid)
            }
            is AuthUiState.Error -> {
                val err = (uiState as AuthUiState.Error)
                authEmailErrorMessage = err.fieldErrors?.email
                authPasswordErrorMessage = err.fieldErrors?.password
                authErrorMessage = "Login Failed"
            }
            else -> {}
        }
    }
    var emailErrorMessage = viewModel.emailErrorMessage(email)
    var passwordErrorMessage = viewModel.passwordErrorMessage(password)
    if(emailErrorMessage.isNullOrEmpty()) emailErrorMessage = authEmailErrorMessage
    if(passwordErrorMessage.isNullOrEmpty()) passwordErrorMessage = authPasswordErrorMessage

    LoginContent(
        email = email,
        onEmailValueChange = { email = it },
        password = password,
        onPasswordValueChange = { password = it },
        rememberMe = rememberMe,
        onRememberMeChanged = { rememberMe = it },
        onLoginClick = {
            firstTime = false
            viewModel.login(email, password)
        },
        onSignUpClick = {
            onSignUpClick()
        },
        inProgress = isLoading,
        onForgotPasswordClick = { },
        onPasswordVisibilityChange = {
            passwordVisible = !passwordVisible
        },
        passwordVisible = passwordVisible,
        emailErrorMessage = emailErrorMessage,
        passwordErrorMessage = passwordErrorMessage,
        authErrorMessage = authErrorMessage,
        firstTime = firstTime,

    )
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    email: String,
    onEmailValueChange: (String) -> Unit,
    password: String,
    onPasswordValueChange: (String) -> Unit,
    rememberMe: Boolean,
    onRememberMeChanged: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    inProgress: Boolean,
    onForgotPasswordClick: () -> Unit = {},
    onPasswordVisibilityChange : () -> Unit = {},
    passwordVisible : Boolean = false,
    emailErrorMessage : String? = "",
    passwordErrorMessage : String? = "",
    authErrorMessage : String? = "",
    firstTime : Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Curve()
        }

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(60.dp)
                    .background(color = Color(0xFFFF5722))
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                title = "Email",
                value = email,
                onValueChange = onEmailValueChange,
                icon = Icons.Filled.Email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            ValidateErrorText(
                isError = !firstTime and !emailErrorMessage.isNullOrBlank(),
                errorText = emailErrorMessage ?: ""
            )

            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                title = "Password",
                value = password,
                placeholder = "Enter Your Password",
                onValueChange = onPasswordValueChange,
                icon = Icons.Default.Lock,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = onPasswordVisibilityChange) {
                        Icon(icon, contentDescription = "Toggle Confirm Password Visibility")
                    }
                },
            )
            ValidateErrorText(
                isError = !firstTime and !passwordErrorMessage.isNullOrEmpty(),
                errorText = passwordErrorMessage ?: ""
            )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(
//                        checked = rememberMe,
//                        onCheckedChange = onRememberMeChanged,
//                    )
//                    Text(text = "Remember Me")
//                }
//                TextButton(onClick = { }) {
//                    Text("Forgot Password?", color = Color(0xFFFF5722))
//                }
//            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                if (inProgress) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Login...", color = Color.White, fontSize = 16.sp)
                }
                else Text("Login", color = Color.White, fontSize = 18.sp)
            }
            ValidateErrorText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                isError = !firstTime and !authErrorMessage.isNullOrEmpty(),
                errorText = authErrorMessage ?: ""
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {

                Text("Don’t have an Account? ")
                Text(
                    "Sign up",
                    color = Color(0xFFFF5722),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onSignUpClick()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
