package com.creator.spotly.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.ui.theme.BUTTON_SIZE
import com.creator.spotly.ui.theme.ButtonContainer
import com.creator.spotly.ui.theme.ROUNDED_BUTTON_VALUE

@Composable
fun CustomIconButton(
    onClick : () -> Unit,
    icon: ImageVector,
    roundedValue: Int = ROUNDED_BUTTON_VALUE,
    buttonSize: Int = BUTTON_SIZE,
    containerColor: Color = ButtonContainer,
    contentColor: Color = Color.Black,
    iconSize: Int = 18
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(roundedValue.dp),
        modifier = Modifier.size(buttonSize.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize.dp).align(Alignment.CenterVertically),

        )
    }
}

@Composable
fun CustomIconButton(
    onClick : () -> Unit,
    roundedValue: Int,
    size: Int,
    icon: Int
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(roundedValue.dp),
        modifier = Modifier.size(size.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray.copy(alpha = 0.2f),
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp).align(Alignment.CenterVertically),

            )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomIconButtonPreview() {
    CustomIconButton(
        onClick = {},
        icon = Icons.Default.ArrowBackIosNew
    )
}