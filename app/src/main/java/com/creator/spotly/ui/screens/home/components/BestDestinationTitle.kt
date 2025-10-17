package com.creator.spotly.ui.screens.home.components

import androidx.compose.foundation.clickable
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
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun BestDestinationTitle(
    viewAllClickHandler : () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Best Destination",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = CustomFontFamily
        )
        Text(
            modifier = Modifier.clickable { viewAllClickHandler() },
            text = "View all",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = CustomFontFamily,
            color = OrangeRed
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BestDestinationTitlePreview() {
    BestDestinationTitle() {}
}