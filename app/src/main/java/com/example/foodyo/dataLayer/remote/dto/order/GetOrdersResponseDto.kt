package com.example.foodyo.dataLayer.remote.dto.order

import com.example.foodyo.dataLayer.remote.dto.cart.AppErrorDto
import kotlinx.serialization.Serializable
@Serializable
data class GetOrdersResponseDto(

    val success: Boolean,

    val message: String,

    val data: List<OrderDto>,

    val error: AppErrorDto?

)