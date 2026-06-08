package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.auth.SignInRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignInResponseDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignUpRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignUpResponseDto
import com.example.foodyo.domainLayer.util.Results


interface AuthRepository {
    suspend fun signIn(
        request: SignInRequestDto
    ): Results<SignInResponseDto>

    suspend fun signUp(
        request: SignUpRequestDto
    ): Results<SignUpResponseDto>

    fun isUserLoggedIn() : Boolean

}