package com.example.foodyo.Presentation.CartUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.cart.CartDto
import com.example.foodyo.dataLayer.remote.dto.cart.CartResponseDto
import com.example.foodyo.domainLayer.usecase.AddToCartUseCase
import com.example.foodyo.domainLayer.usecase.GetCartUseCase
import com.example.foodyo.domainLayer.usecase.RemoveCartItemUseCase
import com.example.foodyo.domainLayer.usecase.UpdateCartItemQuantityUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase,
    private val removeCartItemUseCase: RemoveCartItemUseCase
) : ViewModel() {

    private val _cartState =
        MutableStateFlow<Results<CartResponseDto>>(Results.Idle)
    val cartState = _cartState.asStateFlow()

    // NEW -> Stores latest cart data separately to avoid UI flicker
    private val _cart =
        MutableStateFlow<CartDto?>(null)
    val cart = _cart.asStateFlow()

    fun addToCart(menuId: String) {

        viewModelScope.launch {

            _cartState.value = Results.Loading

            val result = addToCartUseCase(menuId)

            when (result) {


                is Results.Success -> getCart()


                is Results.Failure -> {
                    _cartState.value = result
                }

                else -> {}
            }

        }

    }

    fun getCart() {

        viewModelScope.launch {

            _cartState.value = Results.Loading

            val result = getCartUseCase()

            _cartState.value = result

            if (result is Results.Success) {
                _cart.value = result.data.data
            }

        }

    }

    fun updateCartItemQuantity(
        menuId: String,
        quantity: Int
    ) {

        viewModelScope.launch {

            _cartState.value = Results.Loading

            val result =
                updateCartItemQuantityUseCase(
                    menuId,
                    quantity
                )

            when (result) {

                is Results.Success -> getCart()

                is Results.Failure -> {
                    _cartState.value = result
                }

                else -> {}

            }

        }

    }

    fun removeCartItem(menuId: String) {

        viewModelScope.launch {

            _cartState.value = Results.Loading

            val result =
                removeCartItemUseCase(menuId)

            when (result) {

                is Results.Success -> getCart()

                is Results.Failure -> {
                    _cartState.value = result
                }

                else -> {}

            }

        }

    }

    fun resetCartState() {

        _cartState.value = Results.Idle
        _cart.value = null

    }

}