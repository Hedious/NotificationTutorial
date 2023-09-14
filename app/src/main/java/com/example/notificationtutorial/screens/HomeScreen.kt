package com.example.notificationtutorial.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.notificationtutorial.navigation.NotificationScreen
import com.example.notificationtutorial.notification.sendNotification

@Composable
fun Home(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text(modifier = Modifier.clickable {
            navHostController.navigate(NotificationScreen.Detail.passArgument("From HomeScreen"))
        }, text = "Home", fontStyle = MaterialTheme.typography.labelLarge.fontStyle)
        Button(onClick = {
            sendNotification(
                context,
                "Hello Android Body",
                "Hello Notification"
            )
        }) {
            Text(text = "Show Notification")
        }
    }
}