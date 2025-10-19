package com.creator.spotly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.SpotlyTheme


@Composable
fun ScheduleScreen(


){

    Column(
        modifier = Modifier.padding(16.dp),
        // verticalArrangement = Arrangement.spacedBy(8.dp) // space between items
    ) {
        AppbarSchedule()
        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) { Text(text = "My Schedule", fontSize = 30.sp)
            Text(text = "View all", fontSize = 20.sp, color = Color(0xffFF6421))

        }


        Spacer(modifier = Modifier.height(15.dp))
        ItemSchedule()

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScheduleScreen() {
    SpotlyTheme  {
        ScheduleScreen()
    }
}