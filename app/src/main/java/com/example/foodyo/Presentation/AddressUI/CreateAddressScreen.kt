package com.example.foodyo.Presentation.AddressUI

import android.R.attr.password
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodyo.Navigation.routes
import com.example.foodyo.Presentation.AddressUI.Component.AddressHeader
import com.example.foodyo.Presentation.AddressUI.Component.AddressLabelSelector
import com.example.foodyo.Presentation.AuthUI.Component.CustomTextField
import com.example.foodyo.domainLayer.util.Results
import com.example.foodyo.R
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun CreateAddressScreen(
    viewModel: CreateAddressViewModel = hiltViewModel(),
    navController: NavController
){

    var receiverName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedLabel by remember { mutableStateOf("Home") }
    var addressLine1 by remember { mutableStateOf("") }
    var addressLine2 by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val createAddressState by viewModel.createAddressState.collectAsState()

    LaunchedEffect(createAddressState){

        when(val state = createAddressState){

            is Results.Idle -> {}
            is Results.Loading -> {
                isLoading = true
            }
            is Results.Success -> {
                isLoading = false
                Toast.makeText(
                    context,
                    state.data.message,
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate(routes.Home){
                    popUpTo(routes.CreateAddress){
                        inclusive = true
                    }
                }

                viewModel.resetCreateAddressState()
            }

            is Results.Failure -> {
                isLoading = false
                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Scaffold(
        containerColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(vertical = 20.dp)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            AddressHeader()
            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(
                value = receiverName,
                onValueChange = { receiverName = it },
                label = "Receiver Name",
                placeholder = "Enter receiver name",
                icon = R.drawable.baseline_person_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone Number",
                placeholder = "Enter phone number",
                icon = R.drawable.baseline_phone_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AddressLabelSelector(
                selectedLabel = selectedLabel,
                onLabelSelected = {
                    selectedLabel = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = addressLine1,
                onValueChange = { addressLine1 = it },
                label = "Address Line 1",
                placeholder = "House No., Street...",
                icon = R.drawable.baseline_location_on_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = addressLine2,
                onValueChange = { addressLine2 = it },
                label = "Address Line 2 (Optional)",
                placeholder = "Landmark",
                icon = R.drawable.baseline_location_on_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = city,
                onValueChange = { city = it },
                label = "City",
                placeholder = "City",
                icon = R.drawable.baseline_location_city_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = state,
                onValueChange = { state = it },
                label = "State",
                placeholder = "State",
                icon = R.drawable.baseline_map_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = pincode,
                onValueChange = { pincode = it },
                label = "Pincode",
                placeholder = "Pincode",
                icon = R.drawable.baseline_pin_drop_24,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(OrangeStart)
                    .clickable{
                        if (
                            receiverName.isNotBlank() &&
                            phone.isNotBlank() &&
                            addressLine1.isNotBlank() &&
                            city.isNotBlank() &&
                            state.isNotBlank() &&
                            pincode.isNotBlank()
                        ) {

                            viewModel.createAddress(
                                label = selectedLabel,
                                receiverName = receiverName,
                                phone = phone,
                                addressLine1 = addressLine1,
                                addressLine2 = addressLine2,
                                city = city,
                                state = state,
                                pincode = pincode
                            )

                        } else {

                            Toast.makeText(
                                context,
                                "Please fill all required fields",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            ){
                if(isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White

                    )
                }else{
                    Text(
                        text = "Save Address",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.align(Alignment.Center),
                        letterSpacing = 0.5.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))



        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun prev(){
//    CreateAddressScreen()
//}