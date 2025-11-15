package com.creator.spotly.ui.profile.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.TextFieldContainer

@Composable
fun CustomField(
    modifier: Modifier = Modifier,
    title: String = "First Name",
    value: String = "zanaty",
    onValueChange: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontFamily = CustomFontFamily,
            fontSize = 18.sp,
        )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldContainer
            ),
            textStyle = TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        )

    }
}

@Preview
@Composable
private fun CustomFieldPreview() {
    CustomField(

    )
}