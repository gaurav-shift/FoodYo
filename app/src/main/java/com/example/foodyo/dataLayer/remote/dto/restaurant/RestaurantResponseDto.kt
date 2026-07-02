package com.example.foodyo.dataLayer.remote.dto.restaurant

import com.example.foodyo.dataLayer.remote.dto.auth.ErrorDto
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantResponseDto(
    val success: Boolean,
    val message: String,
    val data: RestaurantDto? = null,
    val error: ErrorDto? = null
)