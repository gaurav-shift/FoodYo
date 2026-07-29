package com.example.foodyo.dataLayer.remote.dto.order
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDto(

    val menuId: String,

    val name: String,

    val image: String,

    val price: Int,

    val quantity: Int,

    val isVeg: Boolean

)