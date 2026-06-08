package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.dataLayer.remote.dto.auth.SignInRequestDto
import com.example.foodyo.dataLayer.remote.dto.auth.SignInResponseDto
import com.example.foodyo.domainLayer.repository.AuthRepository
import com.example.foodyo.domainLayer.util.Results
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Results<SignInResponseDto> {
        return repository.signIn(
            SignInRequestDto(
                email = email,
                password = password
            )
        )
    }
}