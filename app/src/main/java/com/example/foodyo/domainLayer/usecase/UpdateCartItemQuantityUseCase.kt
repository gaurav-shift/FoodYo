package com.example.foodyo.domainLayer.usecase

import com.example.foodyo.domainLayer.repository.CartRepository
import javax.inject.Inject

class UpdateCartItemQuantityUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(
        menuId: String,
        quantity: Int
    ) = repository.updateCartItemQuantity(
        menuId,
        quantity
    )

}