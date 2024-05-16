package com.example.adidas.ui.UserProfileUwU

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    OnForgotPasswordClicked:() -> Unit
){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = OnForgotPasswordClicked) {
            Text(text = "Back To Login")
        }
    }
}