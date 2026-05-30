package com.example.foodyo.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object Splash : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object Signup : Routes
}