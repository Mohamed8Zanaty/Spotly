package com.creator.spotly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppbarSearch(){
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.DarkGray,
                modifier = Modifier.size(40.dp)
            )

        Text(

            text = "Search",
            color = Color.Black,
            fontSize =25.sp,
            fontWeight = FontWeight.Bold,
        )

        TextButton(
            onClick = {  },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xffFF6421)
            )
        ) {
            Text("Cancel", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }



    }

}

