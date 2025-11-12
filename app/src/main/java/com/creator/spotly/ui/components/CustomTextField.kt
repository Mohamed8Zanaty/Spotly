package com.creator.spotly.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    title: String = "Email",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    icon : ImageVector = Icons.Default.Email,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit) = {},
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Text(
        text = title,
        fontSize = 17.sp,
        color = Color.Black
    )
    OutlinedTextField(
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        placeholder = { Text("l  demo@email.com", color = Color.Gray) },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.Gray
            )
        },
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
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField()
}