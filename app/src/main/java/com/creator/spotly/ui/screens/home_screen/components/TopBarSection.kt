package com.creator.spotly.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.R
import com.creator.spotly.ui.sharedelements.CustomIconButton

@Composable
fun TopBarSection(
    notificationsIconHandler: () -> Unit,
    profileIconHandler: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.5.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomIconButton(
            onClick = notificationsIconHandler,
            roundedValue = 25,
            size = 44,
            icon = R.drawable.notifications
        )

        Button(
            onClick = profileIconHandler,
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray.copy(alpha = 0.2f),
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(118.dp)
            ) {
                // we should change this to viewmodel
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = null,
                    modifier = Modifier.size(37.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Imane",

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBArSectionPreview() {
    TopBarSection(
        notificationsIconHandler = {},
        profileIconHandler = {},

    )
}