package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null
)