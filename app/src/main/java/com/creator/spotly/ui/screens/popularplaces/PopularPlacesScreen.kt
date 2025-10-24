package com.creator.spotly.ui.screens.popularplaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.R


data class Place(
    val name: String,
    val location: String,
    val rating: Double,
    val price: Int,
    val imageRes: Int
)


@Composable
fun PopularTopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(Color(0xFFF7F7F9), shape = CircleShape)
                .padding(8.dp)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back to Messeges"
            )
        }

        Text(
            text = "Popular Places",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.size(42.dp))
    }
}

@Composable
fun PlaceCard(place: Place) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(10.dp)
            .width(170.dp)
    ) {
        // Image + heart icon
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .height(120.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(30.dp)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = place.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = place.location,
                color = Color.Gray,
                fontSize = 13.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = place.rating.toString(),
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "$${place.price}/Person",
                fontSize = 13.sp,
                color = Color(0xFFFF6A00),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PlacesScreen() {
    val places = listOf(
        Place("Hisma Desert", "Saudi Arabia", 4.9, 459, R.drawable.desert),
        Place("Hisma Desert", "Zero Point, Sylhet", 4.8, 924, R.drawable.beach),
        Place("The HSB Vortex", "Zero Point, Sylhet", 4.2, 681, R.drawable.mountain),
        Place("Hisma Desert", "Zero Point, Sylhet", 4.5, 977, R.drawable.waterfall)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PopularTopBar()

        Text(
            text = "All Popular Places",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(places) { place ->
                PlaceCard(place)
            }
        }
    }
}

@Preview
@Composable
fun screenPreview() {
    PlacesScreen()
}
