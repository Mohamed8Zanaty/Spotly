package com.example.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
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
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.ui.screens.details.components.DetailsCard


@Composable
fun DetailsScreen(
    onBack: () -> Unit = {}
) {
    DetailsContent(
        onBackButtonClick = onBack,
        onBookMarkButtonClick = { },
    )
}

@Composable
fun DetailsContent(
    onBackButtonClick: () -> Unit = {},
    onBookMarkButtonClick: () -> Unit = {},

) {
    Box(
        modifier = Modifier
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
        /*
        * TODO: Handling back button ( to Home )
        *  Handle BookMark button ( Add it to favorite data of the user )
        * */
        CustomTopbar(
            backButtonHandler = onBackButtonClick,
            title = "Details",
            backContainerColor = Color.Transparent.copy(alpha = 0.4f),
            backContentColor = Color.White,
            titleColor = Color.White,
            item = {
                CustomIconButton(
                    icon = Icons.Default.BookmarkBorder,
                    onClick = { },
                    containerColor = Color.Transparent.copy(alpha = 0.4f),
                    contentColor = Color.White
                )
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            DetailsCard()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {

    DetailsScreen()

}
