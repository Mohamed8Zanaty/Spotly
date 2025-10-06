package com.creator.spotly.ui.sharedelements


import android.graphics.drawable.Icon
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed
import com.creator.spotly.ui.theme.SpotlyTheme
import org.w3c.dom.Text


enum class SelectedButton {
    HOME,
    CALENDER,
    MESSAGES,
    PROFILE
}


@Composable
fun BottomNavBar() {
    var selected by remember { mutableStateOf(SelectedButton.HOME) }

    Box(
        Modifier.fillMaxWidth()
            .height(100.dp)

        ,
        contentAlignment = Alignment.Center,

    ) {

    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CustomButton(
            onClick = {
                selected = SelectedButton.HOME
            },
            selected = selected,
            targetSelected = SelectedButton.HOME,
            icon = Icons.Default.Home,
            text = "Home"
        )
        CustomButton(
            onClick = {
                selected = SelectedButton.CALENDER
            },
            selected = selected,
            targetSelected = SelectedButton.CALENDER,
            icon = Icons.Default.DateRange,
            text = "Calender"
        )
        FAB(
            onClick = {},
           icon = Icons.Default.Search

        )
        CustomButton(
            onClick = {
                selected = SelectedButton.MESSAGES
            },
            selected = selected,
            targetSelected = SelectedButton.MESSAGES,
            icon = Icons.Default.MailOutline,
            text = "Messages"
        )
        CustomButton(
            onClick = {
                selected = SelectedButton.PROFILE
            },
            selected = selected,
            targetSelected = SelectedButton.PROFILE,
            icon = Icons.Default.Person,
            text = "Profile"
        )
    }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    BottomNavBar()
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    selected: SelectedButton,
    targetSelected: SelectedButton,
    icon: ImageVector,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(75.dp)

        ,
        colors = ButtonDefaults.buttonColors(
            contentColor = if(selected == targetSelected) OrangeRed else Color.LightGray,
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),

    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text,
                fontSize = 12.sp,
                fontFamily = CustomFontFamily
            )
        }
    }
}

@Composable
fun FAB(
    onClick: () -> Unit,
    icon: ImageVector
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .size(45.dp)
        ,
        shape = RoundedCornerShape(50.dp),
        containerColor = OrangeRed,
        contentColor = Color.White

    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

