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
package com.example.androiddevchallenge.ui.screens.overviewScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navGraph.Screen
import com.example.androiddevchallenge.ui.state.model.Puppy
import com.example.androiddevchallenge.ui.state.viewModel.AppViewModel
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionAppTheme

@Composable
fun OverviewScreen(
    navHostController: NavHostController,
    appViewModel: AppViewModel,
) {
    val puppies = appViewModel.puppies
    val selectPuppy = appViewModel::selectPuppy

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CallToAction(
            puppies = puppies,
            selectPuppy = selectPuppy,
            navController = navHostController,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pick for keeps",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9593ff),
        )
        Spacer(modifier = Modifier.height(8.dp))
        PuppyGrid(
            puppies = puppies,
            selectPuppy = selectPuppy,
            navHostController = navHostController,
        )
    }
}

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )
        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }
        // Track each columns height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
            .coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.place(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }
}

@Composable
fun PuppyGrid(
    puppies: List<Puppy>,
    selectPuppy: (Puppy) -> Unit,
    navHostController: NavHostController,
) {
    Column {
        VerticalGrid {
            puppies.map {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .aspectRatio(1f)
                        .padding(8.dp)
                        .shadow(8.dp, RoundedCornerShape(32.dp))
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.Black)
                        .clickable(
                            onClickLabel = "select puppy",
                        ) {
                            selectPuppy(it)
                            navHostController.navigate(Screen.DetailScreen.route)
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(32.dp)),
                        painter = painterResource(it.image),
                        contentDescription = "List Image",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
    }
}

@Preview("Light Theme")
@Composable
fun OverviewScreenLightPreview() {
    PuppyAdoptionAppTheme {
        OverviewScreen(
            rememberNavController(),
            AppViewModel()
        )
    }
}
