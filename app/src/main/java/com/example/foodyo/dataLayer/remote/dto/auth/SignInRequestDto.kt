package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val email: String,
    val password: String
)