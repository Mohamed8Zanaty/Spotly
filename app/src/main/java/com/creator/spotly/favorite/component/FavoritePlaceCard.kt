package com.creator.spotly.favorite.component

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import coil3.compose.AsyncImage
import com.creator.spotly.data.dto.PlaceItem

@Composable
fun FavoritePlaceCard(place: PlaceItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {},
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
//                AsyncImage(
//                    model = place.imageUrl,
//                    contentDescription = place.name,
//                    contentScale = ContentScale.Crop,
//                    modifier =
//                        Modifier
//                            .fillMaxSize()
//                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
//                )

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color(0xFFFF2D55),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(20.dp),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = place.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_mylocation),
                    contentDescription = "Location",
                    tint = Color.Gray,
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(4.dp))
//                Text(text = place.address, color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun FavoritePlaceCardPreview() {
//    val place = PlaceItem(
//        name = "Zanaty",
//        address = "El-marg",
//        imageUrl = "blabla"
//    )
//    FavoritePlaceCard(place)
//}
