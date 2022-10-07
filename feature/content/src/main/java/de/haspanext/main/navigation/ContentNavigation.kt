package de.haspanext.main.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.haspanext.main.navigation.ContentRootGraph.CONTENT_ROOT
import de.haspanext.main.ui.ContentRootScreen

sealed interface ContentNavigationDestination {
    object Login: ContentNavigationDestination
    object Detail: ContentNavigationDestination
}

fun NavGraphBuilder.contentGraph(route: String,  onNavigateToNextScreen: (destination: ContentNavigationDestination) -> Unit) {
    navigation(startDestination = CONTENT_ROOT, route = route) {
        composable(CONTENT_ROOT) { ContentRootScreen(onNavigateToNextScreen) }
    }
}

private object ContentRootGraph {
    const val CONTENT_ROOT = "content:root"
}

