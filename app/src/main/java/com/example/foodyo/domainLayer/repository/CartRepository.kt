package com.example.foodyo.domainLayer.repository

import com.example.foodyo.dataLayer.remote.dto.cart.CartResponseDto
import com.example.foodyo.domainLayer.util.Results

interface CartRepository {

    suspend fun addToCart(
        menuId: String
    ): Results<CartResponseDto>

    suspend fun getCart(
    ): Results<CartResponseDto>

    suspend fun updateCartItemQuantity(
        menuId: String,
        quantity: Int
    ): Results<CartResponseDto>

    suspend fun removeCartItem(
        menuId: String
    ): Results<CartResponseDto>

}