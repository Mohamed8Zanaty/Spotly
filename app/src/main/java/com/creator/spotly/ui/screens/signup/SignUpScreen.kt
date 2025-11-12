package com.creator.spotly.ui.screens.signup
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.domain.auth.createUser
import com.creator.spotly.ui.components.Curve
import com.creator.spotly.ui.components.CustomTextField


@Composable
fun SignUpScreen(
    onUserCreatedSuccess: () -> Unit = {}
) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }

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
            createUser(name, email, password, favoritePlaces = emptyList()) { success, error ->
                if(success) {
                    onUserCreatedSuccess()
                } else {
                    // Show error
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    )
}

@Composable
fun SignUpContent(
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
    onSignUp: () -> Unit
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
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Sign Up",
                fontSize = 28.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Divider(
                color = Color(0xFFFF6421),
                thickness = 3.dp,
                modifier = Modifier
                    .width(58.dp)
                    .padding(bottom = 24.dp)
            )
            CustomTextField(
                title = "Name",
                value = name,
                onValueChange = onNameChange,
                icon = Icons.Default.Person,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                title = "Email",
                value = email,
                onValueChange = onEmailChange,
                icon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                title = "Password",
                value = password,
                onValueChange = onPasswordChange,
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
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                title = "Confirm Password",
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                icon = Icons.Default.Lock,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = onConfirmPasswordVisibilityChange) {
                        Icon(icon, contentDescription = "Toggle Confirm Password Visibility")
                    }
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onSignUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6421))
            ) {
                Text("Create Account", color = Color.White, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Already have an account! ", color = Color.Gray)
                Text(
                    text = "Login",
                    color = Color(0xFFFF6421),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                    }
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