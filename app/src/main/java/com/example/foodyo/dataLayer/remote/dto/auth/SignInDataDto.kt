package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInDataDto(
    val user: UserDto,
    val token: String
)