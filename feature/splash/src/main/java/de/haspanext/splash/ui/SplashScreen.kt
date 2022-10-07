package de.haspanext.splash.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import de.haspanext.splash.navigation.SplashNavigationDestination



@Composable
internal fun SplashScreen(onNavigateToNextScreen: (destination: SplashNavigationDestination) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {

        //Niemals in einem richtigen Projekt machen
        Handler(Looper.getMainLooper()).postDelayed(
            {
                onNavigateToNextScreen(SplashNavigationDestination.Authentication)
            },
            2000 // value in milliseconds
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SplashScreen",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(fontSize = 26.sp)
            )
        }
    }
}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen{}
}

