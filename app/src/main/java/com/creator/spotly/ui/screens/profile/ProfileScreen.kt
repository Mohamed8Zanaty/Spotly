package com.creator.spotly
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.components.CustomIconButton
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {  },
    onEditClick: () -> Unit = {  }
) {
    ProfileContent(
        onBackClick = onBackClick,
        onEditClick = onEditClick
    )
}

@Composable
fun ProfileContent(
    onBackClick: () -> Unit = {  },
    onEditClick: () -> Unit = {  }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        CustomTopbar(
            title = "Profile",
            backButtonHandler = onBackClick,
            item = {
                CustomIconButton(
                    onClick = onEditClick,
                    icon = Icons.Default.Edit,
                    contentColor = OrangeRed,
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFDFE6))
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Reyna Mohamed", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Ellaithy@email.com", color = Color.Gray, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF7F7F9), RoundedCornerShape(16.dp))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Text("Reward Points", fontSize = 14.sp, color = Color.Black)
                Text("50", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
            Divider(
                color = Color(0xFFE0E0E0),
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Travel Trips", fontSize = 14.sp, color = Color.Black)
                Text("40", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
            Divider(
                color = Color(0xFFE0E0E0),
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )
            Column (
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Bucket List", fontSize = 14.sp, color = Color.Black)
                Text("200", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6421))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF7F7F9), RoundedCornerShape(16.dp)),

            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.PersonOutline,
                    contentDescription = "Profile",
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Profile",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f)
                        .clickable { }
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
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Bookmarked",
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Bookmarked",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f)
                        .clickable { }
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
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row {
                Icon(
                    imageVector = Icons.Default.Flight,
                    contentDescription = "Previous Trips",
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Previous Trips",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f)
                        .clickable { }
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
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Settings",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f)
                        .clickable { }
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
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Version Info",
                    tint = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Version 1.0.0",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f)
                        .clickable { }
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
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}