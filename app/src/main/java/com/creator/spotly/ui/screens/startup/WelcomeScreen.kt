package com.creator.spotly.ui.screens.startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.creator.spotly.ui.components.Curve
import com.creator.spotly.ui.screens.startup.component.ContinueButton
import com.creator.spotly.ui.screens.startup.component.TopBackground
import com.creator.spotly.ui.screens.startup.component.WelcomeTextSection

@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Curve()
            }
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeTextSection()
            ContinueButton(modifier = Modifier,onClick = {
               print("hallo")
            })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}