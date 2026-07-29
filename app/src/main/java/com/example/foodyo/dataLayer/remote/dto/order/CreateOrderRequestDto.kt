package com.example.foodyo.dataLayer.remote.dto.order
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequestDto(

    val addressId: String

)