package com.example.foodyo.Navigation

import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantDto
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
    data object CreateAddress : routes

    @Serializable
    data object Profile : routes

    @Serializable
    data class UpdateAddress(
        val addressId: String
    ) : routes

    @Serializable
    data class RestaurantDetails(
        val restaurantId: String
    ) : routes
}