import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Presentation.ProfileUI.Components.AddAddressButton
import com.example.foodyo.Presentation.ProfileUI.Components.AddressCard
import com.example.foodyo.Presentation.ProfileUI.Components.LogoutButton
import com.example.foodyo.Presentation.ProfileUI.Components.ProfileHeader
import com.example.foodyo.Presentation.ProfileUI.Components.UserInfoCard
import com.example.foodyo.Presentation.ProfileUI.ProfileViewModel
import com.example.foodyo.domainLayer.util.Results
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.foodyo.Navigation.routes
import com.example.foodyo.Presentation.AddressUI.DeleteAddressViewModel
import com.example.foodyo.dataLayer.remote.dto.address.AddressDto
import androidx.compose.runtime.setValue
import com.example.foodyo.Presentation.AddressUI.UpdateAddressViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val userState by viewModel.userState.collectAsState()
    val addressState by viewModel.addressState.collectAsState()

    val deleteViewModel: DeleteAddressViewModel = hiltViewModel()
    val deleteState by deleteViewModel.deleteAddressState.collectAsState()

    val updateViewModel: UpdateAddressViewModel = hiltViewModel()
    val updateState by updateViewModel.updateAddressState.collectAsState()

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var selectedAddress by remember {
        mutableStateOf<AddressDto?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    LaunchedEffect(updateState){

        when(val state = updateState){

            is Results.Success -> {

                Toast.makeText(
                    context,
                    state.data.message,
                    Toast.LENGTH_SHORT
                ).show()

                updateViewModel.resetUpdateAddressState()

                viewModel.loadProfile()

            }

            is Results.Failure -> {

                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()

                updateViewModel.resetUpdateAddressState()
            }

            else -> {}
        }

    }

    LaunchedEffect(deleteState) {

        when(val state = deleteState){

            is Results.Idle -> {}

            is Results.Loading -> {}

            is Results.Success -> {

                Toast.makeText(
                    context,
                    state.data.message,
                    Toast.LENGTH_SHORT
                ).show()

                deleteViewModel.resetDeleteAddressState()

                viewModel.loadProfile()

            }

            is Results.Failure -> {

                Toast.makeText(
                    context,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()

                deleteViewModel.resetDeleteAddressState()

            }

        }

    }

    when {

        userState is Results.Loading ||
                addressState is Results.Loading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        else -> {

            Scaffold(
                containerColor = Color.Black
            ) { paddingValues ->

                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(Color.Black)
                ) {

                    item {

                        ProfileHeader(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )

                    }

                    when (val user = userState) {

                        is Results.Success -> {

                            item {

                                UserInfoCard(
                                    user = user.data.data!!
                                )

                            }

                        }

                        else -> {}

                    }

                    item {

                        Text(
                            text = "Saved Addresses",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 12.dp
                            )
                        )

                    }

                    when (val address = addressState) {

                        is Results.Success -> {

                            items(address.data.data ?: emptyList()) {

                                AddressCard(
                                    address = it,
                                    onEdit = {

                                        navController.currentBackStackEntry
                                            ?.savedStateHandle
                                            ?.set("address", it)

                                        navController.navigate(
                                            routes.UpdateAddress(it.id)
                                        )

                                    },
                                    onDelete = {
                                        selectedAddress = it
                                        showDeleteDialog = true
                                    },
                                    onMakeDefault = {
                                        updateViewModel.makeDefault(it)
                                    }
                                )

                            }

                        }

                        else -> {}

                    }

                    item {

                        AddAddressButton(
                            onClick = {
                                navController.navigate(routes.CreateAddress)
                            }
                        )

                    }

                    item {

                        LogoutButton(
                            onClick = {

                            }
                        )

                    }

                    item {
                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )
                    }

                }

            }

        }

    }

    if (showDeleteDialog) {

        AlertDialog(

            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text("Delete Address")
            },

            text = {
                Text("Are you sure you want to delete this address?")
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        selectedAddress?.let {

                            deleteViewModel.deleteAddress(
                                it.id
                            )

                        }

                        showDeleteDialog = false

                    }

                ) {

                    Text("Delete")

                }

            },

            dismissButton = {

                TextButton(

                    onClick = {

                        showDeleteDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )

    }



}

