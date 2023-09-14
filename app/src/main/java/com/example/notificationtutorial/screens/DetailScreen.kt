package com.example.notificationtutorial.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Detail(navHostController: NavHostController, message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Detail $message", fontStyle = MaterialTheme.typography.labelLarge.fontStyle)
    }
}