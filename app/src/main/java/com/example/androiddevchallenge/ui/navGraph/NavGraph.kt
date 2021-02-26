package com.example.androiddevchallenge.ui.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.OverviewScreen

sealed class Screen(val route: String, val name: String) {
    object OverviewScreen : Screen("overview_screen", "OverviewScreen")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.OverviewScreen.route) {
        composable(Screen.OverviewScreen.route) {
            OverviewScreen(navController)
        }
    }
}