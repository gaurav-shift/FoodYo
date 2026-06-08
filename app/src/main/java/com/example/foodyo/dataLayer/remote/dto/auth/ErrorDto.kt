package com.example.foodyo.dataLayer.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val code: Int ?= null
)