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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.haspanext.authentication.navigation.AuthenticationGraph
import de.haspanext.authentication.navigation.AuthenticationNavigationDestination
import de.haspanext.authentication.navigation.NavigationState
import de.haspanext.authentication.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

private fun authNavigator(
    state: NavigationState, navController: NavController,
    onNavigateToExternalScreen: (destination: AuthenticationNavigationDestination) -> Unit,
    onNavigated: () -> Unit
) {
    when (state) {
        NavigationState.Main -> onNavigateToExternalScreen(AuthenticationNavigationDestination.Content)
        NavigationState.Register -> navController.navigate(AuthenticationGraph.START_REGISTER)
    }

    //avoid multiple calls when its already idled
    if (state != NavigationState.Idle) onNavigated()
}

@Composable
internal fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToExternalScreen: (destination: AuthenticationNavigationDestination) -> Unit
) {

    val registerButtonClicked =
        { viewModel.onRegisterButtonClicked() }
    val loginButtonClicked =
        {
            viewModel.onLoginButtonClicked()
            //onNavigateToNextScreen(AuthenticationNavigationDestination.Content)
        }

    //val isLoading = viewModel.isLoading
    authNavigator(
        viewModel.navigationState.value,
        navController,
        onNavigateToExternalScreen,
        viewModel::onNavigated
    )
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
    LoginScreen(navController = rememberNavController()) {}
}