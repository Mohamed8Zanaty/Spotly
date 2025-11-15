package com.creator.spotly.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.creator.spotly.ui.theme.CustomFontFamily

@Composable
fun ValidateErrorText(
    modifier: Modifier = Modifier,
    isError: Boolean,
    errorText: String
) {
    if (isError) {
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            fontFamily = CustomFontFamily,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun ValidateErrorTextPreview() {

}