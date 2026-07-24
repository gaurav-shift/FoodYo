package com.example.foodyo.dataLayer.remote.dto.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartResponseDto(
    val success: Boolean,
    val message: String,
    val data: CartDto?,
    val error: CartErrorDto?
)