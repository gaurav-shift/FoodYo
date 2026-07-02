package com.example.foodyo.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface routes {

    @Serializable
    data object Splash : routes

    @Serializable
    data object Home : routes

    @Serializable
    data object Login : routes

    @Serializable
    data object Signup : routes

    @Serializable
    data object CreateAddress

    @Serializable
    data object Profile : routes

    @Serializable
    data class UpdateAddress(
        val addressId: String
    ) : routes
}