package com.example.foodyo.Presentation.OrderUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyo.dataLayer.remote.dto.address.AddressResponseDto
import com.example.foodyo.dataLayer.remote.dto.address.GetAddressesResponseDto
import com.example.foodyo.dataLayer.remote.dto.order.OrderDto
import com.example.foodyo.domainLayer.usecase.CreateOrderUseCase
import com.example.foodyo.domainLayer.usecase.GetAddressByIdUseCase
import com.example.foodyo.domainLayer.usecase.GetAddressesUseCase
import com.example.foodyo.domainLayer.usecase.GetOrderByIdUseCase
import com.example.foodyo.domainLayer.usecase.GetOrdersUseCase
import com.example.foodyo.domainLayer.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getAddressByIdUseCase: GetAddressByIdUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val getAddressesUseCase: GetAddressesUseCase
) : ViewModel() {

    // ---------------- Address ----------------
    private val _getAddressState =
        MutableStateFlow<Results<AddressResponseDto>>(Results.Idle)
    val getAddressState = _getAddressState.asStateFlow()
    var selectedAddress: AddressResponseDto? = null
        private set

    fun getAddressById(id: String) {
        viewModelScope.launch {
            _getAddressState.value = Results.Loading
            val result = getAddressByIdUseCase(id)
            _getAddressState.value = result
            if (result is Results.Success) {
                selectedAddress = result.data
            }
        }
    }

    fun resetGetAddressState() {
        _getAddressState.value = Results.Idle
    }


    // ---------------- Addresses ----------------
    private val _addressState =
        MutableStateFlow<Results<GetAddressesResponseDto>>(Results.Idle)
    val addressState = _addressState.asStateFlow()
    fun getAddresses() {
        viewModelScope.launch {
            _addressState.value = Results.Loading
            _addressState.value = getAddressesUseCase()
        }
    }

    private val _selectedAddressId = MutableStateFlow<String?>(null)
    val selectedAddressId = _selectedAddressId.asStateFlow()

    fun selectAddress(addressId: String) {
        _selectedAddressId.value = addressId
    }

    fun resetAddressState() {
        _addressState.value = Results.Idle
    }


    // ---------------- Create Order ----------------
    private val _createOrderState =
        MutableStateFlow<Results<OrderDto>>(Results.Idle)
    val createOrderState = _createOrderState.asStateFlow()
    fun createOrder(addressId: String) {

        viewModelScope.launch {
            _createOrderState.value = Results.Loading
            _createOrderState.value =
                createOrderUseCase(addressId)
        }
    }

    fun resetCreateOrderState() {
        _createOrderState.value = Results.Idle
    }

    // ---------------- Orders List ----------------

    private val _ordersState =
        MutableStateFlow<Results<List<OrderDto>>>(Results.Idle)
    val ordersState = _ordersState.asStateFlow()
    var orders: List<OrderDto> = emptyList()
        private set

    fun getOrders() {
        viewModelScope.launch {
            _ordersState.value = Results.Loading
            val result = getOrdersUseCase()
            _ordersState.value = result
            if (result is Results.Success) {
                orders = result.data
            }
        }
    }

    fun resetOrdersState() {
        _ordersState.value = Results.Idle
    }

    // ---------------- Selected Order ----------------

    private val _selectedOrderState =
        MutableStateFlow<Results<OrderDto>>(Results.Idle)
    val selectedOrderState = _selectedOrderState.asStateFlow()
    var selectedOrder: OrderDto? = null
        private set
    fun getOrderById(orderId: String) {
        viewModelScope.launch {
            _selectedOrderState.value = Results.Loading
            val result = getOrderByIdUseCase(orderId)
            _selectedOrderState.value = result
            if (result is Results.Success) {
                selectedOrder = result.data
            }
        }

    }

    fun resetSelectedOrderState() {
        _selectedOrderState.value = Results.Idle
    }

    fun clearSelectedAddress() {
        _selectedAddressId.value = null
    }


}