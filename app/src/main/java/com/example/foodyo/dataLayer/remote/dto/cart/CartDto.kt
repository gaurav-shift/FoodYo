package com.example.foodyo.dataLayer.remote.dto.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartDto(


    val id: String,

    val userId: String,

    val restaurantId: String,

    val restaurantName: String,

    val items: List<CartItemDto>,

    val subtotal: Int,

    val deliveryFee: Int,

    val tax: Int,

    val totalAmount: Int

)