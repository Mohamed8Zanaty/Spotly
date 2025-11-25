package com.creator.spotly.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.BUTTON_SIZE
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.ROUNDED_BUTTON_VALUE
import com.creator.spotly.ui.theme.TOPBAR_PADDING_TOP

@Composable
fun CustomTopbar(
    backButtonHandler : () -> Unit,
    title: String,
    item :  @Composable () -> Unit = { Box{} },
    backContainerColor: Color = Color.LightGray.copy(alpha = 0.2f),
    backContentColor: Color = Color.Black,
    titleColor: Color = Color.Black,
    titleFontSize: Int = 20
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = TOPBAR_PADDING_TOP.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // Back Button
        CustomIconButton(
            onClick = backButtonHandler,
            icon = Icons.Default.ArrowBackIosNew,


        )
        // Title
        Text(
            title,
            fontWeight = FontWeight.SemiBold,
            fontSize = titleFontSize.sp,
            fontFamily = CustomFontFamily,

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
                icon = Icons.Default.MoreVert
            )
        }
    )
}