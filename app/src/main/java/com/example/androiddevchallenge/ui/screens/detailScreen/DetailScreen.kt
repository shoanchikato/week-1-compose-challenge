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
package com.example.androiddevchallenge.ui.screens.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.navGraph.Screen.OverviewScreen.route
import com.example.androiddevchallenge.ui.state.viewModel.AppViewModel
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionAppTheme

@Composable
fun DetailScreen(
    navController: NavController,
    appViewModel: AppViewModel,
) {
    val selectedPuppy = appViewModel.selectedPuppy

    if (selectedPuppy == null) {
        navController.navigate(route)
    }

    Column {
        HeroImage(
            image = selectedPuppy.image,
            navController = navController,
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            NameAndPriceRow(
                name = selectedPuppy.name,
                price = selectedPuppy.price
            )
            Spacer(modifier = Modifier.height(24.dp))
            LocationRow(
                locationText = selectedPuppy.location
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp, end = 12.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DescriptionPills(
                    descriptor = "Age",
                    value = selectedPuppy.age,
                    color = Color(0xFF9593ff),
                )
                DescriptionPills(
                    descriptor = "Sex",
                    value = selectedPuppy.sex,
                    color = Color(0xFFFF00a0),
                )
                DescriptionPills(
                    descriptor = "Breed",
                    value = selectedPuppy.breed,
                    color = Color(0xFF00ABFF),
                )
            }
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .width(240.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF3778f8)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Adopt",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun HeroImage(
    image: Int = R.drawable.puppy_11,
    navController: NavController
) {
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
            painter = painterResource(image),
            contentDescription = "List Image",
            contentScale = ContentScale.FillWidth
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 24.dp, start = 24.dp)
                .size(56.dp)
                .shadow(8.dp, CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .clickable(
                    onClickLabel = "back button"
                ) {
                    navController.popBackStack()
                }
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(R.drawable.back),
                contentDescription = "back arrow",
                tint = Color(0xFF676767)
            )
        }
    }
}

@Composable
fun NameAndPriceRow(
    name: String = "Rusty",
    price: String = "$400",
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF676767),
        )

        Text(
            text = price,
            fontSize = 32.sp,
            color = Color(0xFF676767),
        )
    }
}

@Composable
fun LocationRow(
    locationText: String = "134 Singing Forest, Share field"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.pin),
            contentDescription = "location pin",
            tint = Color(0xFF676767),
        )
        Text(
            text = locationText,
            fontSize = 22.sp,
            color = Color(0xFF676767),
        )
    }
}

@Composable
fun DescriptionPills(
    descriptor: String = "Age",
    value: String = "3 months",
    color: Color = Color(0xFF9593ff),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(70.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color)
    ) {
        Text(
            text = descriptor,
            fontSize = 18.sp,
            color = Color.White,
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Preview("Light Theme")
@Composable
fun DetailScreenLightPreview() {
    PuppyAdoptionAppTheme {
        DetailScreen(
            rememberNavController(),
            AppViewModel()
        )
    }
}
