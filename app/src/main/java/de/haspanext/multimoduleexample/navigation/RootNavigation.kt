package de.haspanext.multimoduleexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.haspanext.authentication.navigation.AuthenticationNavigationDestination
import de.haspanext.authentication.navigation.authenticationGraph
import de.haspanext.main.navigation.ContentNavigationDestination
import de.haspanext.main.navigation.contentGraph
import de.haspanext.multimoduleexample.navigation.RootGraph.AUTH
import de.haspanext.multimoduleexample.navigation.RootGraph.CONTENT
import de.haspanext.multimoduleexample.navigation.RootGraph.SPLASH
import de.haspanext.splash.navigation.SplashNavigationDestination
import de.haspanext.splash.navigation.splashScreenGraph


//RootGraph only contains the nested graphs from feature modules. no direct composable init is allowed here
@Composable
internal fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = SPLASH) {
        initNestedSplashGraph(navController)
        initNestedAuthenticationGraph(navController)
        initNestedContentGraph(navController)
    }
}

private fun NavGraphBuilder.initNestedSplashGraph(navController: NavHostController) {

    val splashNavigation = { destination: SplashNavigationDestination ->
        val route = when (destination) {
            SplashNavigationDestination.Authentication -> AUTH
            SplashNavigationDestination.Content -> CONTENT
        }
        navController.navigate(route) {
           popUpTo(SPLASH) { inclusive = true }
        }
    }
    splashScreenGraph(splashNavigation, SPLASH)
}

private fun NavGraphBuilder.initNestedAuthenticationGraph(navController: NavHostController) {

    val authNavigation = { destination: AuthenticationNavigationDestination ->
        val route = when (destination) {
            AuthenticationNavigationDestination.Content -> CONTENT
        }
        navController.navigate(route)
    }

    authenticationGraph(
        navController,
        authNavigation,
        AUTH
    )
}

private fun NavGraphBuilder.initNestedContentGraph(navController: NavHostController) {

    val contentNavigation = { destination: ContentNavigationDestination ->
        when (destination) {
            ContentNavigationDestination.Detail -> TODO()
            ContentNavigationDestination.Login -> navController.navigate(AUTH) {
                launchSingleTop = true
                popUpTo(0)
                //popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }

    }
    contentGraph(CONTENT, contentNavigation)
}

private object RootGraph {
    const val SPLASH = "root_splash"
    const val AUTH = "root_auth"
    const val CONTENT = "root_content"
}