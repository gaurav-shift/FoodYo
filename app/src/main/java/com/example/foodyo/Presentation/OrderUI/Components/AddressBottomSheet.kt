package com.example.foodyo.Presentation.OrderUI.Components

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodyo.Presentation.OrderUI.OrderViewModel
import com.example.foodyo.domainLayer.util.Results

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(

    onDismiss: () -> Unit,
    onAddNewAddress: () -> Unit,
    onDeliverHere: (String) -> Unit,
    orderViewModel: OrderViewModel = hiltViewModel()
) {

    val addressState by orderViewModel.addressState.collectAsState()
    val selectedAddressId by orderViewModel.selectedAddressId.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.getAddresses()
    }

    LaunchedEffect(addressState) {

        val state = addressState
        if (
            state is Results.Success &&
            selectedAddressId == null
        ) {
            val defaultAddress =
                state.data.data
                    ?.firstOrNull { it.isDefault }

            defaultAddress?.let {
                orderViewModel.selectAddress(it.id)
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest ={
            orderViewModel.clearSelectedAddress()
            onDismiss()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Choose Delivery Address",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            when (val state = addressState) {
                Results.Idle -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                Results.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is Results.Failure -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(state.message)
                        OutlinedButton(
                            onClick = {
                                orderViewModel.resetAddressState()
                                orderViewModel.getAddresses()
                            }
                        ) {
                            Text("Retry")
                        }
                    }
                }

                is Results.Success -> {
                    val addresses = state.data.data ?: emptyList()
                    if (addresses.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("No saved addresses found.")
                            Button(
                                onClick = {
                                    onDismiss()
                                    onAddNewAddress()
                                }
                            ){
                                Text("Add Address")
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.heightIn(max = 450.dp),
                            contentPadding = PaddingValues(bottom = 8.dp)
                        ) {
                            items(addresses) { address ->
                                OrderAddressCard(
                                    address = address,
                                    isSelected = selectedAddressId == address.id,
                                    onClick = {
                                        orderViewModel.selectAddress(address.id)
                                    }
                                )
                            }
                        }
                    }
                }

            }

            Divider()
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onDismiss()
                    onAddNewAddress()
                }
            ) {
                Text("+ Add New Address")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = selectedAddressId != null,
                onClick = {
                    selectedAddressId?.let {
                        onDeliverHere(it)
                    }
                    onDismiss()
                }
            ) {
                Text("Deliver Here")
            }
        }
    }
}