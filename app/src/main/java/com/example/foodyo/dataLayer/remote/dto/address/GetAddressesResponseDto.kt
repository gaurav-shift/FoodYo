package com.example.foodyo.dataLayer.remote.dto.address
import kotlinx.serialization.Serializable

@Serializable
data class GetAddressesResponseDto(
    val success: Boolean,
    val message: String,
    val data: List<AddressDto> = emptyList(),
    val error: AddressErrorDto? = null
)