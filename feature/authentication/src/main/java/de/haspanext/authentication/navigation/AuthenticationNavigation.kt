package de.haspanext.authentication.navigation

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.haspanext.authentication.navigation.AuthenticationGraph.LOGIN
import de.haspanext.authentication.navigation.AuthenticationGraph.START_REGISTER
import de.haspanext.authentication.ui.LoginScreen
import de.haspanext.authentication.ui.RegisterScreen
import kotlin.random.Random

sealed interface AuthenticationNavigationDestination {
    object Content : AuthenticationNavigationDestination
}

fun NavGraphBuilder.authenticationGraph(
    navController: NavController,
    onNavigateToNextScreen: (destination: AuthenticationNavigationDestination) -> Unit,
    route: String
) {
    Log.i("prose", "Init AuthGraph: ${navController.backQueue.size}")
    val authNavigation = { destination: AuthenticationNavigationDestination ->
        when (destination) {
            AuthenticationNavigationDestination.Content -> onNavigateToNextScreen(
                AuthenticationNavigationDestination.Content
            )
        }
    }

    navigation(startDestination = LOGIN, route = route) {
        composable(LOGIN) {
            Log.i("prose", "Inside LoginIsNext")
            LoginScreen(navController=navController) {
                authNavigation(it)
            }
        }
        composable(START_REGISTER) {
            Log.i("prose", "Inside RegisterIsNext")
            RegisterScreen(navController)
        }
    }
}

internal object AuthenticationGraph {
    const val LOGIN = "auth:login"
    const val START_REGISTER = "auth:start_register"
}