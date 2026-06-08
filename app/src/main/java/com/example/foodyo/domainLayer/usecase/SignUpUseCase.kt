package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.dataLayer.remote.dto.auth.SignUpRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignUpResponseDto
import com.example.foodyo.domainLayer.repository.AuthRepository
import com.example.foodyo.domainLayer.util.Results
import jakarta.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): Results<SignUpResponseDto> {

        return repository.signUp(
            SignUpRequestDto(
                name = name,
                email = email,
                password = password
            )
        )
    }
}