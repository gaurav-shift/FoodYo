package com.example.foodyo.Presentation.HomeUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto
import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantListResponseDto
import com.example.foodyo.domainLayer.usecase.GetAddressesUseCase
import com.example.foodyo.domainLayer.usecase.GetRestaurantsByCityUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val getAddressesUseCase: GetAddressesUseCase,
    private val getRestaurantsByCityUseCase: GetRestaurantsByCityUseCase

) : ViewModel() {
    private val _restaurantState =
        MutableStateFlow<Results<RestaurantListResponseDto>>(
            Results.Idle
        )

    val restaurantState =
        _restaurantState.asStateFlow()

    private val _defaultAddress =
        MutableStateFlow<AddressDto?>(null)

    val defaultAddress =
        _defaultAddress.asStateFlow()

    fun loadHome() {

        _restaurantState.value = Results.Loading

        viewModelScope.launch(Dispatchers.IO) {

            val addressResult = getAddressesUseCase()

            when (addressResult) {

                is Results.Success -> {

                    val defaultAddress =
                        addressResult.data.data
                            ?.firstOrNull { it.isDefault }

                    if (defaultAddress == null) {

                        _restaurantState.value =
                            Results.Failure(
                                "No default address found"
                            )

                        return@launch
                    }
                    _defaultAddress.value = defaultAddress
                    val restaurantResult =
                        getRestaurantsByCityUseCase(
                            defaultAddress.city
                        )

                    _restaurantState.value =
                        restaurantResult
                }

                is Results.Failure -> {

                    _restaurantState.value =
                        Results.Failure(
                            addressResult.message
                        )
                }

                else -> {}
            }
        }
    }

    fun resetRestaurantState() {
        _restaurantState.value = Results.Idle
    }
}