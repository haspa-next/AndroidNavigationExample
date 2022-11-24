package de.haspanext.authentication.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import de.haspanext.authentication.navigation.NavTarget
import de.haspanext.authentication.navigation.NavigationState
import de.haspanext.authentication.navigation.Navigator
import javax.inject.Inject

internal class LoginViewModel (private val navigator: Navigator): ViewModel() {
    init {
        Log.i("prose", "Init LoginViewModel")
    }

    val isLoading = mutableStateOf(false)

    var navigationState = mutableStateOf<NavigationState>(NavigationState.Idle)
        private set

    fun onLoginButtonClicked() {
        isLoading.value = true
        Handler(Looper.getMainLooper()).postDelayed(
            {
                isLoading.value = false
                navigationState.value = NavigationState.Main
            },
            2000 // value in milliseconds
        )
    }

    fun onRegisterButtonClicked() {
        //navigationState.value = NavigationState.Register
        navigator.navigateTo(NavTarget.Register)
    }

    fun onNavigated() {
        navigationState.value = NavigationState.Idle
    }
}