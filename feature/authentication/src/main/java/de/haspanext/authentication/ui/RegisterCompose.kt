package de.haspanext.authentication.ui

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.haspanext.authentication.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Cyan
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Registration",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(fontSize = 26.sp)
            )
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}