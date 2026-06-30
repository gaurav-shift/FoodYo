package com.example.foodyo.dataLayer.remote.dto.address

import kotlinx.serialization.Serializable

@Serializable
data class ValidationDetailDto(
    val type: String? = null,
    val value: String? = null,
    val msg: String? = null,
    val path: String? = null,
    val location: String? = null
)