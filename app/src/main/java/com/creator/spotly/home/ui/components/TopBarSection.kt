package com.creator.spotly.home.ui.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.R

@Composable
fun TopBarSection(
    profileButtonHandler: () -> Unit,
    profileIcon: Int = R.drawable.user,
    name: String = "User Name",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = profileButtonHandler,
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.height(44.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray.copy(alpha = 0.15f),
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ) {
                // we should change this to viewmodel
                Image(
                    painter = painterResource(id = profileIcon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = name,
                    fontSize = 16.sp
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarSectionPreview() {
    TopBarSection(
        profileButtonHandler = {},
        )
}