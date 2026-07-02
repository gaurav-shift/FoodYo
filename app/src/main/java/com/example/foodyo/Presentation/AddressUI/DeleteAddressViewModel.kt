package com.example.foodyo.Presentation.AddressUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.DeleteAddressResponseDto
import com.example.foodyo.domainLayer.usecase.DeleteAddressUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAddressViewModel @Inject constructor(

    private val deleteAddressUseCase: DeleteAddressUseCase

) : ViewModel() {

    private val _deleteAddressState =
        MutableStateFlow<Results<DeleteAddressResponseDto>>(
            Results.Idle
        )

    val deleteAddressState =
        _deleteAddressState.asStateFlow()

    fun deleteAddress(
        id: String
    ) {

        viewModelScope.launch {

            _deleteAddressState.value = Results.Loading

            _deleteAddressState.value =
                deleteAddressUseCase(id)

        }

    }

    fun resetDeleteAddressState() {
        _deleteAddressState.value = Results.Idle
    }

}