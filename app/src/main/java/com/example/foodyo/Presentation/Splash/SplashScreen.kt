package com.example.foodyo.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodyo.R
import com.example.foodyo.ui.theme.OrangeEnd
import com.example.foodyo.ui.theme.OrangeStart
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToLogin()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        OrangeStart,
                        OrangeEnd
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(0.dp))

        Text(
            text = buildAnnotatedString {

                withStyle(
                    SpanStyle(
                        color = Color.White
                    )
                ) {
                    append("FOOD")
                }

                withStyle(
                    SpanStyle(
                        color = Color(0xFFFFD54F)
                    )
                ) {
                    append("Yo!")
                }
            },
            fontSize = 64.sp,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "YOUR DELICIOUS DELIVERIES",
            color = Color.White.copy(alpha = 0.95f),
            fontSize = 16.sp,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen()
//}