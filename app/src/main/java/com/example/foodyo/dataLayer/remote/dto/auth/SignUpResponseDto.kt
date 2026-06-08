package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    val success: Boolean,
    val message: String,
    val data: UserDto? = null,
    val error: ErrorDto? = null
)