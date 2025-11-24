package com.creator.spotly.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.CustomFontFamily

@Composable
fun BestDestinationTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Best Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = CustomFontFamily
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BestDestinationTitlePreview() {
    BestDestinationTitle()
}