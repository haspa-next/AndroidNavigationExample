package de.haspanext.authentication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.haspanext.authentication.ui.LoginScreen
import de.haspanext.authentication.ui.RegisterScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.java.KoinJavaComponent.get

@Composable
fun NavigationComponent(onNavigateToNextScreen: (destination: AuthenticationNavigationDestination) -> Unit,
) {
    val navController = rememberNavController()
    val navigator: Navigator = get(Navigator::class.java)

    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.route)
        }.launchIn(this)
    }

    NavHost(navController = navController, startDestination = NavTarget.Login.route) {
        composable(NavTarget.Login.route) {
            LoginScreen()
        }
        composable(NavTarget.Register.route) {
            Log.i("prose", "Inside RegisterIsNext")
            RegisterScreen()
        }
    }
}