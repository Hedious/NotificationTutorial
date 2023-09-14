package com.example.notificationtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notificationtutorial.screens.Detail
import com.example.notificationtutorial.screens.Home
import com.example.notificationtutorial.navigation.NotificationScreen
import com.example.notificationtutorial.navigation.SetupNavGraph
import com.example.notificationtutorial.ui.theme.NotificationTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationTutorialTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(navController)
                }
            }
        }

//        FirebaseMessaging.getInstance()?.token?.addOnCompleteListener {
//            Log.d("firebase_token", it.result)
//        }
    }
}



