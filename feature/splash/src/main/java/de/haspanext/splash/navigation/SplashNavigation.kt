package de.haspanext.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.haspanext.splash.navigation.SplashGraph.SPLASH
import de.haspanext.splash.ui.SplashScreen

//is used for communication outside of the Module
sealed interface SplashNavigationDestination {
    object Authentication : SplashNavigationDestination
    object Content : SplashNavigationDestination
}

fun NavGraphBuilder.splashScreenGraph(
    onNavigateToNextScreen: (destination: SplashNavigationDestination) -> Unit,
    route: String
) {
    navigation(startDestination = SPLASH, route = route) {
        composable(SPLASH) { SplashScreen(onNavigateToNextScreen) }
    }
}

private object SplashGraph {
    const val SPLASH = "splash:splash"
}
