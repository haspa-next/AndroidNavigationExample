package de.haspanext.authentication.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.haspanext.authentication.navigation.AuthenticationGraph
import de.haspanext.authentication.navigation.AuthenticationNavigationDestination

@Composable
internal fun LoginScreen(navController: NavController, onNavigateToNextScreen:(destination: AuthenticationNavigationDestination) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
       Log.i("prose", "LoginScreen BS Count: ${navController.backQueue.size}")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                navController.navigate(AuthenticationGraph.START_REGISTER)
            }) {
                Text(text = "Register")
            }

            Button(onClick = {
                onNavigateToNextScreen(AuthenticationNavigationDestination.Content)
            }) {
                Text(text = "Login")
            }
        }
    }
}



@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController()) {}
}