package de.haspanext.authentication.navigation

sealed interface NavigationState {
    object Idle: NavigationState
    object Main: NavigationState
    object Register: NavigationState
}