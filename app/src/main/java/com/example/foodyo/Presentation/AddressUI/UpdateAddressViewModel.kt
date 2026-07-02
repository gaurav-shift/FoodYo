package com.example.foodyo.Presentation.AddressUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressRequestDto
import com.example.foodyo.dataLayer.remote.dto.address.UpdateAddressResponseDto
import com.example.foodyo.domainLayer.usecase.UpdateAddressUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateAddressViewModel @Inject constructor(

    private val updateAddressUseCase: UpdateAddressUseCase

) : ViewModel() {

    private val _updateAddressState =
        MutableStateFlow<Results<UpdateAddressResponseDto>>(Results.Idle)

    val updateAddressState =
        _updateAddressState.asStateFlow()

    fun updateAddress(

        id: String,

        label: String,

        receiverName: String,

        phone: String,

        addressLine1: String,

        addressLine2: String,

        city: String,

        state: String,

        pincode: String,

        isDefault: Boolean? = null

    ) {

        viewModelScope.launch {

            _updateAddressState.value = Results.Loading

            val request = UpdateAddressRequestDto(

                label = label,
                receiverName = receiverName,
                phone = phone,
                addressLine1 = addressLine1,
                addressLine2 = addressLine2,
                city = city,
                state = state,
                pincode = pincode,
                isDefault = isDefault

            )

            _updateAddressState.value =
                updateAddressUseCase(
                    id,
                    request
                )
        }
    }

    fun resetUpdateAddressState() {
        _updateAddressState.value = Results.Idle
    }
    fun makeDefault(address: AddressDto){

        updateAddress(

            id = address.id,

            label = address.label,

            receiverName = address.receiverName,

            phone = address.phone,

            addressLine1 = address.addressLine1,

            addressLine2 = address.addressLine2,

            city = address.city,

            state = address.state,

            pincode = address.pincode,

            isDefault = true

        )

    }
}