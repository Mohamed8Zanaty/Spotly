package com.creator.spotly.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creator.spotly.domain.model.Category
import com.creator.spotly.ui.theme.CustomFontFamily
import com.creator.spotly.ui.theme.OrangeRed

@Composable
fun Categories(categories: List<Category>, onSelect: (Category) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categories) { cat ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelect(cat) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (cat.imageRes != null) {
                        Image(
                            painter = painterResource(id = cat.imageRes),
                            contentDescription = cat.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                            ,
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                        )
                    }
                     Text(
                        cat.title,
                         modifier = Modifier.padding(vertical = 12.dp),

                        fontSize = 14.sp,
                        fontFamily = CustomFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = OrangeRed
                    )
                }

            }
        }
    }
}