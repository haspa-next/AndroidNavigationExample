package de.haspanext.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.haspanext.authentication.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
) {

    val registerButtonClicked =
        { viewModel.onRegisterButtonClicked() }
    val loginButtonClicked =
        {
            viewModel.onLoginButtonClicked()
            //onNavigateToNextScreen(AuthenticationNavigationDestination.Content)
        }

    //val isLoading = viewModel.isLoading
    LoginContent(
        isLoading = viewModel.isLoading.value,
        onLoginButtonClicked = loginButtonClicked,
        onRegisterButtonClicked = registerButtonClicked
    )
}


@Composable
private fun LoginContent(
    isLoading: Boolean,
    onLoginButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onRegisterButtonClicked) {
                Text(text = "Register")
            }

            Button(onClick = onLoginButtonClicked) {
                Text(text = "Login")
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}