package com.example.foodyo.dataLayer.remote.dto.cart


import kotlinx.serialization.Serializable

@Serializable
data class CartItemDto(
    val menuId: String,
    val name: String,
    val image: String,
    val price: Int,
    val quantity: Int,
    val isVeg: Boolean
)