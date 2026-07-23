package com.example.foodyo.Presentation.MenuUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.restaurant.RestaurantResponseDto
import com.example.foodyo.domainLayer.usecase.GetRestaurantByIdUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(

    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase

) : ViewModel() {

    private val _restaurantState =
        MutableStateFlow<Results<RestaurantResponseDto>>(Results.Idle)

    val restaurantState =
        _restaurantState.asStateFlow()

    fun getRestaurantById(
        restaurantId: String
    ) {

        viewModelScope.launch {

            _restaurantState.value = Results.Loading

            _restaurantState.value =
                getRestaurantByIdUseCase(restaurantId)

        }

    }

    fun resetRestaurantState() {
        _restaurantState.value = Results.Idle
    }

}