package com.example.foodyo.Presentation.AuthUI

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodyo.Navigation.routes
import com.example.foodyo.Presentation.AuthUI.Component.CustomTextField
import com.example.foodyo.R
import com.example.foodyo.domainLayer.util.Results
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
       // Log.d("AUTH_STATE", authState.toString())
        when(val currentState = authState){
            is Results.Failure ->{
                isLoading = false
                Toast.makeText(context, currentState.message, Toast.LENGTH_LONG).show()
                viewModel.resetAuthState()
            }
            Results.Idle -> isLoading = false
            Results.Loading -> isLoading = true
            is Results.Success ->{
                isLoading=false
                Toast.makeText(context,"Account created successfully",Toast.LENGTH_SHORT).show()
                navController.navigate(routes.Login){
                    popUpTo(routes.Signup){inclusive =true}
                }
                viewModel.resetAuthState()
            }
        }
    }

    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = androidx.compose.ui.graphics.Color.Black)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Cravings satisfied, hunger denied.",
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "Create New Account",
                    color = androidx.compose.ui.graphics.Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    value = name,
                    onValueChange = {name = it},
                    label = "Enter Your Full Name",
                    placeholder = "Name",
                    icon = R.drawable.baseline_person_24,
                    modifier = Modifier .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))

                CustomTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = "Email",
                    placeholder = "Email",
                    icon = R.drawable.baseline_email_24,
                    modifier = Modifier .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                CustomTextField(
                    value = password,
                    onValueChange = {password = it },
                    label = "Password",
                    placeholder = "Password",
                    icon = R.drawable.baseline_password_24,
                    modifier = Modifier .padding(horizontal = 16.dp),
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onVisibilityChange = { passwordVisible = !passwordVisible }
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(45.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(OrangeStart)
                        .clickable{
                             if(email.isNotBlank() && password.isNotBlank() && name.isNotBlank()){
                                viewModel.signUp(name,email,password)
                            }
                            else Toast.makeText(context,"All fields required",Toast.LENGTH_SHORT).show()
                        }
                ){
                    if(isLoading){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    } else{
                        Text(
                            text = "Create an Account",
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.Center),
                            letterSpacing = 0.5.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text("Already Have An Account ",
                        fontSize = 18.sp ,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        color = OrangeStart,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable{
                             navController.navigate(routes.Login){
                                    popUpTo(routes.Signup) { inclusive = true }
                             }
                        }
                    )
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun signUpScreenPreview() {
//    SignUpScreen()
//}