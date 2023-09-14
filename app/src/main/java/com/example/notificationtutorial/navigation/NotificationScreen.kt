package com.example.notificationtutorial.navigation


sealed class NotificationScreen(val route: String) {
    object Home : NotificationScreen(route = "home")
    object Detail : NotificationScreen(route = "detail/{$MY_ARG}") {
        fun passArgument(message: String) = "detail/$message"
    }
}