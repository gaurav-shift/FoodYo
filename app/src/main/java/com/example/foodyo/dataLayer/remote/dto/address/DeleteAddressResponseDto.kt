package com.example.foodyo.dataLayer.remote.dto.address

import kotlinx.serialization.Serializable

@Serializable
data class DeleteAddressResponseDto(
    val success: Boolean,
    val message: String,
    val data: Nothing? = null,
    val error: AddressErrorDto? = null
)