package com.example.foodyo.dataLayer.remote.dto.address


import kotlinx.serialization.Serializable

@Serializable
data class AddressResponseDto(
    val success: Boolean,
    val message: String,
    val data: AddressDto?=null,
    val error: AddressErrorDto? = null
)