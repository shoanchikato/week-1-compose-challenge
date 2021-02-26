package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionAppTheme
import com.example.androiddevchallenge.R

@Composable
fun OverviewScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .shadow(12.dp, RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF9593ff))
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
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
                    Column(
                        modifier = Modifier
                            .height(30.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
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

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .aspectRatio(1f)

                ) {
                    Image(
                        painter = painterResource(R.drawable.puppy_4),
                        contentDescription ="Overview Image",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview("Light Theme")
@Composable
fun OverviewScreenLightPreview() {
    PuppyAdoptionAppTheme {
        OverviewScreen(rememberNavController())
    }
}

