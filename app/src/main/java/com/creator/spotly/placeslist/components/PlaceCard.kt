package com.creator.spotly.placeslist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.R
import com.creator.spotly.ui.components.CustomIconButton
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed


@Composable
fun PlaceCard(
    modifier: Modifier = Modifier,
    title: String = "Place Name",
    address: String? = "Address",
    imageRes: Int = R.drawable.cafe,
    onFavoriteClick: (Boolean) -> Unit = {},
    onAddToBucketListClick: (Boolean) -> Unit = {},
    onAddToTripsListClick: (Boolean) -> Unit = {}

    ) {
    var isFavorite by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    var isCart by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .clip(RoundedCornerShape(25.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)

                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = CustomFontFamily
                    )
                    Text(
                        text = address?:"",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = CustomFontFamily
                    )
                }
                CustomIconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        onFavoriteClick(isFavorite)
                    },
                    icon = Icons.Default.FavoriteBorder,
                     contentColor = if (isFavorite) OrangeRed else Color.Black
                )
                CustomIconButton(
                    onClick = {
                        isChecked = !isChecked
                        onAddToTripsListClick(isChecked)
                    },
                    icon = Icons.Default.Check,
                    contentColor = if (isChecked) OrangeRed else Color.Black
                )
                CustomIconButton(
                    onClick = {
                        isCart = !isCart
                        onAddToBucketListClick(isCart)
                    },
                    icon = Icons.Default.CardTravel,
                    contentColor = if (isCart) OrangeRed else Color.Black
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaceCardPreview() {
    PlaceCard()
}