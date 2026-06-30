package com.example.foodyo.dataLayer.remote.dto.address

import kotlinx.serialization.Serializable

@Serializable
data class AddressErrorDto(
    val statusCode: Int? = null,
    val details: List<ValidationDetailDto>? = null
)