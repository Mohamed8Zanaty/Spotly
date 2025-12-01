package com.creator.spotly.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.creator.spotly.ui.components.Curve

@Composable
fun Curve() {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val path = Path().apply {
            moveTo(0f, size.height * 0.7f)
            cubicTo(
                size.width * 0.25f, size.height,
                size.width * 0.75f, size.height * 0.4f,
                size.width, size.height * 0.7f
            )
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
        drawPath(path, color = Color(0xFFFF5722))
    }

}

@Preview(showBackground = true)
@Composable
private fun CurvePreview() {
   Curve()
}