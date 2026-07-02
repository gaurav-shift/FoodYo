package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.AuthRepository
import jakarta.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() =
        repository.getCurrentUser()
}