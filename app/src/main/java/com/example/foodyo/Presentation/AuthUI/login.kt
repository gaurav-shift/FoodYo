package com.example.foodyo.Presentation.AuthUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodyo.Presentation.AuthUI.Component.CustomTextField
import com.example.foodyo.R
import com.example.foodyo.ui.theme.OrangeStart

@Composable
fun LoginScreen() {

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
                    text = "Login to your account",
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
                    value = "",
                    onValueChange = {},
                    label = "Email",
                    placeholder = "Email",
                    icon = R.drawable.baseline_email_24,
                    modifier = Modifier .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Password",
                    placeholder = "Password",
                    icon = R.drawable.baseline_password_24,
                    modifier = Modifier .padding(horizontal = 16.dp),
                    isPassword = true
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangeStart
                    )
                ) {
                    Text(
                        text = "Login With Mail",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text("Create An Account ",
                        fontSize = 18.sp ,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign Up",
                        fontSize = 18.sp,
                        color = OrangeStart,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable{
                           // navController.navigate(Routes.Signup)
                        }
                    )
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun loginScreenPreview() {
//    LoginScreen()
//}