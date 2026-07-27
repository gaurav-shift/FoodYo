package com.example.foodyo.dataLayer.remote.dto.cart

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.Serializable

@Serializable
data class AppErrorResponseDto(
    val success: Boolean,
    val message: String,
    val data: JsonElement? = null,
    val error: AppErrorDto? = null
)

@Serializable
data class AppErrorDto(
    val code: Int
)