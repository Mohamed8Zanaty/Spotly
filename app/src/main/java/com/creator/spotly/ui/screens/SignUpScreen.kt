package com.creator.spotly.ui.screens
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen()
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignUpScreen() {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
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
        Text(
            text = "Email",
            fontSize = 17.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("l  demo@email.com", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(vertical = 8.dp)
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray
            ),
            singleLine = true
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Phone Number",
            fontSize = 17.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            placeholder = { Text("l  +00 000-0000-000", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Password",
            fontSize = 17.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("l  enter your password", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = "Toggle Password Visibility")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray
            ),
            singleLine = true
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Confirm Password",
            fontSize = 17.sp,
            color = Color.Black
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("l  confirm your password", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
            trailingIcon = {
                val icon = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(icon, contentDescription = "Toggle Confirm Password Visibility")
                }
            },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.DarkGray
            ),
            singleLine = true
        )
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { },
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