package de.haspanext.main.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.haspanext.main.navigation.ContentNavigationDestination

sealed class HomeScreen(val route: String, val name: String) {
    object Home : HomeScreen("content_start", "Start")
    object Search : HomeScreen("content_search", "Suche")
    object Profile : HomeScreen("content_profile", "Profil")
}

val items = listOf(
    HomeScreen.Home,
    HomeScreen.Search,
    HomeScreen.Profile
)

@Composable
fun ContentRootScreen(onNavigateToNextScreen: (destination: ContentNavigationDestination) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(screen.name) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        BottomNavigationGraph(
            navController = navController,
            innerPadding = innerPadding,
            onNavigateToNextScreen
        )
    }
}

@Composable
private fun BottomNavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNavigateToNextScreen: (destination: ContentNavigationDestination) -> Unit
) {
    NavHost(
        navController,
        startDestination = HomeScreen.Home.route,
        Modifier.padding(innerPadding)
    ) {
        items.forEach { screen ->
            when (screen) {
                is HomeScreen.Profile -> composable(screen.route) {
                    ProfileScreen(
                        onNavigateToNextScreen = onNavigateToNextScreen
                    )
                }
                else -> composable(screen.route) { DummyScreen(screenName = screen.name) }
            }


        }
    }
}

@Preview
@Composable
fun ContentRootScreenPreview() {
    ContentRootScreen {}
}