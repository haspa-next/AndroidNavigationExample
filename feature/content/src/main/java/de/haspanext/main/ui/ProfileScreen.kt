package de.haspanext.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import de.haspanext.main.navigation.ContentNavigationDestination

@Composable
fun ProfileScreen(onNavigateToNextScreen: (destination: ContentNavigationDestination) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Yellow
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile",
                style = TextStyle(fontSize = 26.sp)
            )
            Button(onClick = { onNavigateToNextScreen(ContentNavigationDestination.Login) }) {
                Text(text = "Log out!")
            }
        }
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    ProfileScreen {}
}