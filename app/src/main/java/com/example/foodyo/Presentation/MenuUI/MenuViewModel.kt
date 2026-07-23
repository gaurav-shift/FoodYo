package com.example.foodyo.Presentation.MenuUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.menu.GetMenusResponseDto
import com.example.foodyo.dataLayer.remote.dto.menu.MenuResponseDto
import com.example.foodyo.domainLayer.usecase.GetMenuByIdUseCase
import com.example.foodyo.domainLayer.usecase.GetMenusByRestaurantUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(

    private val getMenusByRestaurantUseCase: GetMenusByRestaurantUseCase,
    private val getMenuByIdUseCase: GetMenuByIdUseCase

) : ViewModel() {

    private val _menusState =
        MutableStateFlow<Results<GetMenusResponseDto>>(Results.Idle)
    val menusState = _menusState.asStateFlow()

    private val _menuState =
        MutableStateFlow<Results<MenuResponseDto>>(Results.Idle)
    val menuState = _menuState.asStateFlow()

    fun getMenusByRestaurantId(
        restaurantId: String
    ) {

        viewModelScope.launch {

            _menusState.value = Results.Loading

            _menusState.value =
                getMenusByRestaurantUseCase(restaurantId)

        }

    }

    fun getMenuById(
        id: String
    ) {

        viewModelScope.launch {

            _menuState.value = Results.Loading

            _menuState.value =
                getMenuByIdUseCase(id)

        }

    }

    fun resetMenusState() {
        _menusState.value = Results.Idle
    }

    fun resetMenuState() {
        _menuState.value = Results.Idle
    }

}