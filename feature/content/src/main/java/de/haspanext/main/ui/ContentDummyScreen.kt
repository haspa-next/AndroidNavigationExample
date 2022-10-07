package de.haspanext.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun DummyScreen(screenName: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            ScreenText(screenName)

        }
    }
}

@Composable
fun ScreenText(screenName: String) {
    Text(text = "Screen-Number:  $screenName!")
}

@Composable
@Preview
fun previewPlaceHolderScreen() {
    DummyScreen(screenName = "Name")
}