package com.example.foodyo.dataLayer.remote.dto.cart


import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequestDto(
    val menuId: String
)