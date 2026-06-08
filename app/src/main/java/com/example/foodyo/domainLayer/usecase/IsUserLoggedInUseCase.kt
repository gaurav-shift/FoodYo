package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() : Boolean{
        return repository.isUserLoggedIn()
    }
}