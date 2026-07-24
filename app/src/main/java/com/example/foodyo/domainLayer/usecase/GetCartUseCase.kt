package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke() =
        repository.getCart()

}