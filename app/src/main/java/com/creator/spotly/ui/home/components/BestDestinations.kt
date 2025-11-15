package com.creator.spotly.ui.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed


data class TravelItem(
    val id: String,
    val imageUrl: String,
    val title: String,
    val rating: String,
    val location: String,
    val extraCount: Int = 0
)

@Composable
fun TravelCard(
    item: TravelItem,
    modifier: Modifier = Modifier,
    avatarSize: Dp = 28.dp,
    onClick: (String) -> Unit = {},
    iconColor : Color = OrangeRed,
    placeIconColor : Color = Color(0xff7D848D)
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .wrapContentHeight()
            .clickable { onClick(item.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(260.dp)
                    .fillMaxWidth()

            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // bookmark icon
                IconButton(
                    onClick = { /*bookmark*/ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)

                        ,
                    shape = CircleShape,

                ) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = iconColor
                    )
                }
            }

            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = CustomFontFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            contentDescription = "rating",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xffFFD336)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = item.rating,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "location",
                            modifier = Modifier.size(12.dp),
                            tint = placeIconColor
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            fontFamily = CustomFontFamily,
                            fontSize = 14.sp,
                            text = item.location,
                            color = placeIconColor
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun TravelCardRow(
    items: List<TravelItem>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    itemSpacing: Dp = 12.dp,
    onItemClick: (placeID : String) -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(items, key = { it.id }) { item ->
            TravelCard(
                item = item,
                modifier = Modifier
                    .padding(vertical = 4.dp),
                onClick = onItemClick
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun PreviewTravelCardRow() {
    val sample = List(5) { i ->
        TravelItem(
            id = "id_$i",
            imageUrl = "https://www.planetware.com/wpimages/2020/01/india-in-pictures-beautiful-places-to-photograph-taj-mahal.jpg",
            title = "Khai island beach",
            rating = "4.9",
            location = "Thailand",
            extraCount = 50
        )
    }

    TravelCardRow(items = sample)
}
