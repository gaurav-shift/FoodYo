package com.example.foodyo.dataLayer.remote.dto.order

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(

    @SerialName("_id")
    val id: String,

    val userId: String,

    val restaurantId: String,

    val restaurantName: String,

    val address: AddressDto,

    val items: List<OrderItemDto>,

    val subtotal: Int,

    val deliveryFee: Int,

    val tax: Int,

    val totalAmount: Int,

    val paymentMethod: String,

    val paymentStatus: String,

    val orderStatus: String,

    val createdAt: String,

    val updatedAt: String,

    @SerialName("__v")
    val version: Int

)