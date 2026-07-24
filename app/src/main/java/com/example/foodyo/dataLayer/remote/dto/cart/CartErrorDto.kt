package com.example.foodyo.dataLayer.remote.dto.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartErrorDto(
    val statusCode: Int,
    val details: List<CartErrorDetailDto>
)

@Serializable
data class CartErrorDetailDto(
    val type: String,
    val msg: String,
    val path: String,
    val location: String
)