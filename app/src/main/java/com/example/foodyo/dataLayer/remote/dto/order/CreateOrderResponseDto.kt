package com.example.foodyo.dataLayer.remote.dto.order

import com.example.foodyo.dataLayer.remote.dto.cart.AppErrorDto
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderResponseDto(

    val success: Boolean,

    val message: String,

    val data: OrderDto?,

    val error: AppErrorDto?

)