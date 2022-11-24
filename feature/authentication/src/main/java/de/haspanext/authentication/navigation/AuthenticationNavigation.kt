package de.haspanext.authentication.navigation

import android.util.Log

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.haspanext.authentication.ui.LoginScreen
import de.haspanext.authentication.ui.RegisterScreen


sealed interface AuthenticationNavigationDestination {
    object Content : AuthenticationNavigationDestination
}

fun NavGraphBuilder.authenticationGraph(
    navController: NavController,
    onNavigateToNextScreen: (destination: AuthenticationNavigationDestination) -> Unit,
    route: String,
) {

    val externalNavigation = { destination: AuthenticationNavigationDestination ->
        when (destination) {
            AuthenticationNavigationDestination.Content -> onNavigateToNextScreen(
                AuthenticationNavigationDestination.Content
            )
        }
    }

    navigation(startDestination = NavTarget.Login.route, route = route) {
        composable(NavTarget.Login.route) {
            LoginScreen()
        }
        composable(NavTarget.Register.route) {
            Log.i("prose", "Inside RegisterIsNext")
            RegisterScreen()
        }
    }
}