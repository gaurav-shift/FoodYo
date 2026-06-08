package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val name: String,
    val email: String,
    val password: String
)