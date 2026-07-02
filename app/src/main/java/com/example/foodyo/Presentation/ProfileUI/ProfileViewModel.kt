package com.example.foodyo.Presentation.ProfileUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.GetAddressesResponseDto
import com.example.foodyo.dataLayer.remote.dto.auth.GetCurrentUserResponseDto
import com.example.foodyo.domainLayer.usecase.GetAddressesUseCase
import com.example.foodyo.domainLayer.usecase.GetCurrentUserUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import jakarta.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAddressesUseCase: GetAddressesUseCase

) : ViewModel() {

    private val _userState =
        MutableStateFlow<Results<GetCurrentUserResponseDto>>(
            Results.Idle
        )
    val userState = _userState.asStateFlow()

    private val _addressState =
        MutableStateFlow<Results<GetAddressesResponseDto>>(
            Results.Idle
        )
    val addressState = _addressState.asStateFlow()

    fun loadProfile() {

        viewModelScope.launch {

            _userState.value = Results.Loading
            _addressState.value = Results.Loading

            _userState.value =
                getCurrentUserUseCase()

            _addressState.value =
                getAddressesUseCase()

        }
    }

    fun resetUserState() {
        _userState.value = Results.Idle
    }

    fun resetAddressState() {
        _addressState.value = Results.Idle
    }
}