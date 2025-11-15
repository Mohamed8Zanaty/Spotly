package com.creator.spotly.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier,
    text1: String,
    text2: String
) {

    val customText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 38.sp
            )
        ) {
            append(text1)
        }
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 45.sp,
                color = OrangeRed
            )
        ) {
            append(text2)
        }
    }
    Text(
        modifier = modifier.fillMaxWidth(),
        lineHeight = 50.sp,
        text = customText,
        fontFamily = CustomFontFamily,

        )
}

@Preview(showBackground = true)
@Composable
private fun TitleSectionPreview() {
    TitleSection(
        text1 = "Discover the wonders of the ",
        text2 = "world!"
    )
}