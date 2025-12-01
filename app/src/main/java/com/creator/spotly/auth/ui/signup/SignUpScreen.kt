package com.creator.spotly.auth.ui.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
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
import com.creator.spotly.auth.viewmodel.AuthViewModel
import com.creator.spotly.ui.components.Curve
import com.creator.spotly.ui.components.CustomTextField
import com.creator.spotly.ui.components.ValidateErrorText
import com.creator.spotly.ui.state.AuthUiState

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onSignUpSuccess: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
) {

  val uiState by viewModel.state.collectAsState()

  val context = LocalContext.current
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var passwordVisible by remember { mutableStateOf(false) }
  var confirmPasswordVisible by remember { mutableStateOf(false) }

  val isLoading = uiState is AuthUiState.Loading
  var authError by remember { mutableStateOf<String?>(null) }

  LaunchedEffect(uiState) {
    when (uiState) {
      is AuthUiState.Success -> {
        onSignUpSuccess((uiState as AuthUiState.Success).uid)
      }
      is AuthUiState.Error -> {
        authError = "Sign Up Failed"
      }
      else -> {}
    }
  }
  val nameErrorMessage = viewModel.nameErrorMessage(name)
  val emailErrorMessage = viewModel.emailErrorMessage(email)
  val passwordErrorMessage = viewModel.passwordErrorMessage(password)
  val confirmPasswordErrorMessage = viewModel.confirmPasswordErrorMessage(password, confirmPassword)
  var isFirstTime by remember { mutableStateOf(true) }

  SignUpContent(
      email = email,
      password = password,
      confirmPassword = confirmPassword,
      name = name,
      passwordVisible = passwordVisible,
      confirmPasswordVisible = confirmPasswordVisible,
      onEmailChange = { email = it },
      onPasswordChange = { password = it },
      onConfirmPasswordChange = { confirmPassword = it },
      onNameChange = { name = it },
      onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
      onConfirmPasswordVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible },
      onSignUp = {
        viewModel.signUp(name, email, password, confirmPassword)
        isFirstTime = false
      },
      inProgress = isLoading,
      onLoginClick = onLoginClick,
      nameErrorMessage = nameErrorMessage,
      emailErrorMessage = emailErrorMessage,
      passwordErrorMessage = passwordErrorMessage,
      confirmPasswordErrorMessage = confirmPasswordErrorMessage,
      isFirstTime = isFirstTime,
      authError = authError,
  )
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    confirmPassword: String,
    name: String,
    onNameChange: (String) -> Unit,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onConfirmPasswordVisibilityChange: () -> Unit,
    onSignUp: () -> Unit,
    inProgress: Boolean = false,
    onLoginClick: () -> Unit = {},
    nameErrorMessage: String? = null,
    emailErrorMessage: String? = null,
    passwordErrorMessage: String? = null,
    confirmPasswordErrorMessage: String? = null,
    isFirstTime: Boolean,
    authError: String? = null,
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Box(modifier = Modifier.weight(1f).fillMaxWidth()) { Curve() }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.Start,
    ) {
      Text(
          text = "Sign Up",
          fontWeight = FontWeight.Bold,
          fontSize = 28.sp,
          color = Color.Black,
          modifier = Modifier.padding(bottom = 4.dp),
      )
      HorizontalDivider(
          modifier = Modifier.width(58.dp).padding(bottom = 24.dp),
          thickness = 3.dp,
          color = Color(0xFFFF6421),
      )
      CustomTextField(
          title = "Name",
          value = name,
          placeholder = "Enter Your Name",
          onValueChange = onNameChange,
          icon = Icons.Default.Person,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      )
      ValidateErrorText(
          isError = !isFirstTime and !nameErrorMessage.isNullOrBlank(),
          errorText = nameErrorMessage ?: "",
      )
      Spacer(modifier = Modifier.height(16.dp))
      CustomTextField(
          title = "Email",
          value = email,
          onValueChange = onEmailChange,
          icon = Icons.Default.Email,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      )
      ValidateErrorText(
          isError = !isFirstTime and !emailErrorMessage.isNullOrBlank(),
          errorText = emailErrorMessage ?: "",
      )
      Spacer(modifier = Modifier.height(16.dp))
      CustomTextField(
          title = "Password",
          value = password,
          placeholder = "Enter Your Password",
          onValueChange = onPasswordChange,
          icon = Icons.Default.Lock,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation =
              if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
          trailingIcon = {
            val icon =
                if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = onPasswordVisibilityChange) {
              Icon(icon, contentDescription = "Toggle Confirm Password Visibility")
            }
          },
      )
      ValidateErrorText(
          isError = !isFirstTime and !passwordErrorMessage.isNullOrBlank(),
          errorText = passwordErrorMessage ?: "",
      )
      Spacer(modifier = Modifier.height(16.dp))
      CustomTextField(
          title = "Confirm Password",
          value = confirmPassword,
          placeholder = "Confirm Your Password",
          onValueChange = onConfirmPasswordChange,
          icon = Icons.Default.Lock,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation =
              if (confirmPasswordVisible) VisualTransformation.None
              else PasswordVisualTransformation(),
          trailingIcon = {
            val icon =
                if (confirmPasswordVisible) Icons.Default.Visibility
                else Icons.Default.VisibilityOff
            IconButton(onClick = onConfirmPasswordVisibilityChange) {
              Icon(icon, contentDescription = "Toggle Confirm Password Visibility")
            }
          },
      )
      ValidateErrorText(
          isError = !isFirstTime and !confirmPasswordErrorMessage.isNullOrBlank(),
          errorText = confirmPasswordErrorMessage ?: "",
      )
      Spacer(modifier = Modifier.height(24.dp))
      Button(
          onClick = onSignUp,
          enabled = !inProgress,
          modifier = Modifier.fillMaxWidth().height(50.dp),
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6421)),
      ) {
        if (inProgress) {
          CircularProgressIndicator(
              color = Color.White,
              modifier = Modifier.size(24.dp),
              strokeWidth = 2.dp,
          )
          Spacer(modifier = Modifier.width(16.dp))
          Text("Signing Up...", color = Color.White, fontSize = 16.sp)
        } else Text("Create Account", color = Color.White, fontSize = 16.sp)
      }
      ValidateErrorText(
          modifier = Modifier.align(Alignment.CenterHorizontally),
          isError = !isFirstTime and !authError.isNullOrBlank(),
          errorText = authError ?: "",
      )
      Spacer(modifier = Modifier.height(16.dp))
      Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(text = "Already have an account! ")
        Text(
            text = "Login",
            color = Color(0xFFFF6421),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onLoginClick() },
        )
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpPreview() {
  SignUpScreen()
}
