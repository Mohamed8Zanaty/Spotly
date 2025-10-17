package com.creator.spotly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.BUTTON_SIZE
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.ROUNDED_BUTTON_VALUE

@Composable
fun CustomTopbar(
    backButtonHandler : () -> Unit,
    item :  @Composable () -> Unit = { Box{} },
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Back Button
        CustomIconButton(
            onClick = backButtonHandler,
            roundedValue = ROUNDED_BUTTON_VALUE,
            size = BUTTON_SIZE,
            icon = Icons.Default.KeyboardArrowLeft
        )
        Text(
            title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            fontFamily = CustomFontFamily
        )
        item()
    }

}

@Preview(showBackground = true)
@Composable
private fun CustomTopbarPreview() {
    CustomTopbar(
        backButtonHandler = {},
        title = "Messages",
        item = {
            CustomIconButton(
                onClick = {},
                roundedValue = ROUNDED_BUTTON_VALUE,
                size = BUTTON_SIZE,
                icon = Icons.Default.MoreVert
            )
        }
    )
}