package com.example.foodyo.dataLayer.remote.dto.address

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAddressRequestDto(
    val label: String? = null,
    val receiverName: String? = null,
    val phone: String? = null,
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val pincode: String? = null,
    val isDefault: Boolean? = null
)