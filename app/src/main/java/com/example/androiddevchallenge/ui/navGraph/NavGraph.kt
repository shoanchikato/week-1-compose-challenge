/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.navGraph

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.detailScreen.DetailScreen
import com.example.androiddevchallenge.ui.screens.overviewScreen.OverviewScreen
import com.example.androiddevchallenge.ui.state.viewModel.AppViewModel

sealed class Screen(val route: String, val name: String) {
    object OverviewScreen : Screen("overview_screen", "OverviewScreen")
    object DetailScreen : Screen("detail_screen", "DetailScreen")
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()
    NavHost(navController, startDestination = Screen.OverviewScreen.route) {
        composable(Screen.OverviewScreen.route) {
            OverviewScreen(navController, appViewModel)
        }
        composable(Screen.DetailScreen.route) {
            DetailScreen(navController, appViewModel)
        }
    }
}
