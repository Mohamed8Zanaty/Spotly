package com.creator.spotly


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



data class Data(
    val image: Int,
    val title: String,
    val date: String,
    val rating: Double,
    val joinedPeoplePic: Int,
    val joinedPeoplePic2: Int,
    val joinedPeoplePic3: Int,
    val joinedPeople: Int,
    val price: String
)

val trips = listOf(
    Data(R.drawable.pic1, "Niladri Reservoir", "16 July-28 July", 4.8, R.drawable.per1,R.drawable.per2 ,R.drawable.per3,24, "$900"),
    Data(R.drawable.pic2, "Niladri Reservoir", "16 July-28 July", 4.8, R.drawable.per4,R.drawable.per3 ,R.drawable.per5,24, "$900"),
    Data(R.drawable.pic3, "Niladri Reservoir", "16 July-28 July", 4.8, R.drawable.per2,R.drawable.per5 ,R.drawable.per1,24, "$900"),
    Data(R.drawable.pic4, "Niladri Reservoir", "16 July-28 July", 4.8, R.drawable.per3,R.drawable.per1 ,R.drawable.per2,24, "$900"),
)

@Composable
fun PopularPackagesScreen(
    popularPackages: List<Data> = trips
) {


    PopularPackagesContent(trips = trips)


}

@Composable
fun PopularPackagesContent(
    trips: List<Data>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Popular Packages",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp)
        )
        Text(
            text = "All Popular Trip Packages",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 35.dp)
                .align(Alignment.Start)
        )
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ){
            items(trips){data ->
                Cards(data)
            }
        }
    }
}

@Composable
fun Cards (data : Data) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    )
    {
        Row {
            Image(
                painter = painterResource(id = data.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = 160.dp, width = 140.dp)
                    .padding(14.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column {
                Row {
                    Text(
                        text = data.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Surface(
                        color = Color(0xFFFF6421),
                        shadowElevation = 4.dp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 18.dp)
                    ) {
                        Text(
                            text = data.price,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(6.dp),
                            color = Color.White
                        )
                    }

                }
                Row (
                ){
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = Color.Gray ,
                    )
                    Text(
                        text = data.date,
                        fontSize = 15.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Row{
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD336),
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD336),
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD336),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "${data.rating}",
                        fontSize = 16.sp,
                    )
                }
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                        text = "${data.joinedPeople} Person Joined",
                        fontSize = 15.sp,
                        color = Color.Gray,
                )
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPopularPackages() {
    PopularPackagesScreen()
}
