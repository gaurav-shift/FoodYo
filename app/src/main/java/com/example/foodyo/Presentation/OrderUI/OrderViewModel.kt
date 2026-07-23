package com.example.foodyo.Presentation.OrderUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.AddressResponseDto
import com.example.foodyo.domainLayer.usecase.GetAddressByIdUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import jakarta.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getAddressByIdUseCase: GetAddressByIdUseCase
) : ViewModel() {

    private val _getAddressState = MutableStateFlow<Results<AddressResponseDto>>(Results.Idle)
    val getAddressState = _getAddressState.asStateFlow()

    fun getAddressById(id: String) {

        viewModelScope.launch {
            _getAddressState.value = Results.Loading
            _getAddressState.value =
                getAddressByIdUseCase(id)
        }

    }

    fun resetGetAddressState() {
        _getAddressState.value = Results.Idle
    }

}