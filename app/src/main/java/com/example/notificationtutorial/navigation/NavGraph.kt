package com.example.notificationtutorial.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.notificationtutorial.screens.Detail
import com.example.notificationtutorial.screens.Home

const val MY_URI = "https://stevdza-san.com"
const val MY_ARG = "message"

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NotificationScreen.Home.route) {

        composable(
            route = NotificationScreen.Home.route,
//            deepLinks = listOf<NavDeepLink>(NavDeepLink("www.google.com"))
        ) {
            Home(navHostController = navController)
        }

        composable(
            route = NotificationScreen.Detail.route,
            arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
//          deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/{$MY_ARG}" })
        ) {
            val arguments = it.arguments?.getString(MY_ARG)?.let { message ->
                Detail(navHostController = navController, message)
            }

        }
    }
}