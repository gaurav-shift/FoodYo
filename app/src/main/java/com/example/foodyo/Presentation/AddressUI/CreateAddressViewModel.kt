package com.example.foodyo.Presentation.AddressUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.CreateAddressResponseDto
import com.example.foodyo.domainLayer.usecase.CreateAddressUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAddressViewModel @Inject constructor(

    private val createAddressUseCase: CreateAddressUseCase

) : ViewModel() {

    private val _createAddressState =
        MutableStateFlow<Results<CreateAddressResponseDto>>(Results.Idle)

    val createAddressState =
        _createAddressState.asStateFlow()

    fun createAddress(
        label: String,
        receiverName: String,
        phone: String,
        addressLine1: String,
        addressLine2: String,
        city: String,
        state: String,
        pincode: String
    ) {

        _createAddressState.value = Results.Loading

        viewModelScope.launch(Dispatchers.IO) {

            val result = createAddressUseCase(
                label = label,
                receiverName = receiverName,
                phone = phone,
                addressLine1 = addressLine1,
                addressLine2 = addressLine2,
                city = city,
                state = state,
                pincode = pincode
            )

            _createAddressState.value = result
        }
    }

    fun resetCreateAddressState() {
        _createAddressState.value = Results.Idle
    }
}