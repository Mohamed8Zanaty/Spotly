package com.creator.spotly.ui.screens.search.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
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
import com.creator.spotly.R
import com.creator.spotly.ui.theme.SpotlyTheme

@Composable
fun ItemSearch() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(260.dp)
                        .padding(2.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Image(

                            painter = painterResource(id = R.drawable.alex),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = "place photo",
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(150.dp),

                            )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Alexandria Library",
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "Like",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Alexandria",
                                color = Color.Gray
                            )
                        }
                        Row {
                            Text(
                                text = "20$/",
                                color = Color(0xffFF6421)
                            )
                            Text(
                                text = "Person",
                                color = Color.Gray
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    SpotlyTheme {
        ItemSearch()
    }
}