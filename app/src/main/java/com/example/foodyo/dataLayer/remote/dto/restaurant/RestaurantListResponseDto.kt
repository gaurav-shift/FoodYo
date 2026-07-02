package com.example.foodyo.dataLayer.remote.dto.restaurant

import com.example.foodyo.dataLayer.remote.dto.auth.ErrorDto
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantListResponseDto(
    val success: Boolean,
    val message: String,
    val data: List<RestaurantDto>? = null,
    val error: ErrorDto? = null
)