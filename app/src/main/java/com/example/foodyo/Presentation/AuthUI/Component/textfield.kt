package com.example.foodyo.Presentation.AuthUI.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import com.example.foodyo.R

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: Int,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onVisibilityChange: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { androidx.compose.material3.Text(label) },
        placeholder={androidx.compose.material3.Text(placeholder)},
        leadingIcon = { androidx.compose.material3.Icon(painterResource(id = icon), contentDescription = null) },
        trailingIcon = {
            if (isPassword) {
                val visibilityIcon = if (passwordVisible) R.drawable.ic_eye_off else R.drawable.eyes
                androidx.compose.material3.IconButton(onClick = onVisibilityChange) {
                    androidx.compose.material3.Icon(painter = painterResource(id = visibilityIcon), contentDescription = null)
                }
            }
        },
        singleLine = true,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(

            // Background
            focusedContainerColor = Color(0xFF1E1E1E),
            unfocusedContainerColor = Color(0xFF1E1E1E),

            // Text
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,

            // Label
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,

            // Placeholder
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,

            // Leading Icon
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White,

            // Border
            focusedBorderColor = Color(0xFFFF7A00),
            unfocusedBorderColor = Color.DarkGray
        )
    )
}