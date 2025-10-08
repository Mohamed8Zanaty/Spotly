package com.creator.spotly.ui.screens.startup_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creator.spotly.ui.screens.startup_screen.component.ContinueButton
import com.creator.spotly.ui.screens.startup_screen.component.TopBackground
import com.creator.spotly.ui.screens.startup_screen.component.WelcomeTextSection

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().then(modifier)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBackground()
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeTextSection()
            ContinueButton(modifier = Modifier,onClick = {
               print("hallo")
            })
        }
    }
}