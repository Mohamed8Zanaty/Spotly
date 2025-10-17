package com.example.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.R


@Composable
fun DetailsScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.khai_island),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Text(
                text = "Details",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = Color.Black
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Khai island beach",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Chang Wat Phang-nga",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.avatar2), // replace
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                        Text("Thailand", color = Color.Gray, fontSize = 14.sp)

                        Spacer(modifier = Modifier.width(10.dp))

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(16.dp)
                        )
                        Text("4.9", color = Color.Black, fontSize = 14.sp)
                        Text("(280)", color = Color.Gray, fontSize = 13.sp)
                    }

                    Text(
                        text = "$80/Person",
                        color = Color(0xFFFF6A00),
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(5) { index ->
                        Image(
                            painter = painterResource(id = R.drawable.khai_island),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "About Destination",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    buildAnnotatedString {
                        append(
                            "Experience the ultimate vacation package with our beach travel package. From airline tickets to recommended hotel rooms and transportation, we have everything you need ETC..."
                        )
                        withStyle(style = SpanStyle(color = Color(0xFFFFA000), fontWeight = FontWeight.Medium)) {
                            append(" Read More")
                        }
                    },
                    color = Color.Gray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6A00)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Book Now",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun DetailsScreenPreview() {

    DetailsScreen()

}
