package com.example.foodyo.dataLayer.remote.dto.restaurant

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val cuisine: List<String>,
    val address: String,
    val city: String,
    val state: String,
    val pincode: String,
    val rating: Double,
    val deliveryTime: Int,
    val isOpen: Boolean
)