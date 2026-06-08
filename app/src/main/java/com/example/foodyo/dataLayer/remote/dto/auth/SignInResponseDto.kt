package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val success: Boolean,
    val message: String,
    val data: SignInDataDto? = null,
    val error: ErrorDto? = null
)