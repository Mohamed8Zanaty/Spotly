package com.creator.spotly.ui.screens.search.components


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.creator.spotly.ui.components.CustomTopbar
import com.creator.spotly.ui.theme.CustomFontFamily

@Composable
fun AppbarSearch(
    backButtonHandler: () -> Unit,
    cancelHandler: () -> Unit,
) {
    CustomTopbar(
        backButtonHandler = backButtonHandler,
        title = "Search",
        item = {
            TextButton(
                onClick = { },
                colors =
                    ButtonDefaults.textButtonColors(
                        contentColor = Color(0xffFF6421),
                    ),
            ) {
                Text(
                    "Cancel",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = CustomFontFamily
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppbarSearchPreview() {
    AppbarSearch(
        backButtonHandler = {},
        cancelHandler = {},
    )
}