package com.creator.spotly.ui.screens.startup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TopBackground(modifier: Modifier = Modifier) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    Color(0xFFFF5B00), // orange tone
                                    Color(0xFFFF6E00),
                                )
                        ),
                    shape = RoundedCornerShape(bottomStart = 100.dp),
                )
    )
}
