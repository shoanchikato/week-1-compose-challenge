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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.navGraph.Screen
import com.example.androiddevchallenge.ui.state.model.Puppy

@Composable
fun CallToAction(
    puppies: List<Puppy>,
    selectPuppy: (Puppy) -> Unit,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF9593ff))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Looking \nfor \ncuteness",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                CallToActionButton(
                    selectPuppy = selectPuppy,
                    puppies = puppies,
                    navController = navController
                )
            }
            CallToActionImage()
        }
    }
}

@Composable
fun CallToActionImage() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .aspectRatio(1f)

    ) {
        Image(
            painter = painterResource(R.drawable.puppy_4),
            contentDescription = "Overview Image",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun CallToActionButton(
    selectPuppy: (Puppy) -> Unit,
    puppies: List<Puppy>,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .height(30.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable(
                onClickLabel = "Randomly select a puppy",
            ) {
                val randomIndex = (0..21).random()
                val randomlySelectedPuppy = puppies[randomIndex]
                selectPuppy(randomlySelectedPuppy)
                navController.navigate(Screen.DetailScreen.route)
            }
    ) {
        Text(
            modifier = Modifier.width(150.dp),
            text = "super adorable",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9593ff),
            textAlign = TextAlign.Center,
        )
    }
}
