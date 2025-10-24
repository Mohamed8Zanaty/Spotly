package com.creator.spotly.ui.screens.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun TitleSection(
    text1: String,
    text2: String
) {
    val customText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 37.sp
            )
        ) {
            append(text1)
        }
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 38.sp,
                color = OrangeRed
            )
        ) {
            append(text2)
        }
    }
    Text(
        text = customText,
        fontFamily = CustomFontFamily,

        )
}

@Preview(showBackground = true)
@Composable
private fun TitleSectionPreview() {
    TitleSection(
        "Discover the wonders of the ",
        "world!"
    )
}