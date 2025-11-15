package com.creator.spotly.ui.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun OptionRow(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.PersonOutline,
    title: String = "Profile",
    iconColor: Color = OrangeRed,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            icon,
            contentDescription = title,
            tint = iconColor,
            modifier = Modifier.padding(8.dp),
        )
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(12.dp)
                .weight(1f)
                ,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            fontFamily = CustomFontFamily

        )
        Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "Go",
            tint = Color.Gray,
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OptionRowPreview() {
    OptionRow()
}