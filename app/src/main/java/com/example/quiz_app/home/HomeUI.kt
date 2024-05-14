package com.example.quiz_app.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz_app.AppData
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.R
import com.example.quiz_app.userName.shapes

private var homeViewModel: HomeViewModel? = null

@Composable
fun HomeScreen(onShopClick: () -> Unit = {}, onQuizSelect: () -> Unit = {}) {
    homeViewModel = viewModel(modelClass = HomeViewModel::class.java)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar()
        Spacer(modifier = Modifier.padding(10.dp))
        HomeMainContent(onQuizSelect)
        HomeBottomNav(onShopClick)
    }
}

@Composable
fun HomeTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(0.dp, 10.dp, 20.dp, 0.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_icon),
                    contentDescription = "User icon",
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = AppData.userName,
                    color = colorResource(id = R.color.white),
                    fontSize = 28.sp,
                    fontWeight = FontWeight(600),
                    softWrap = true
                )
            }
        }
        Box {
            Box(modifier = Modifier
                .background(colorResource(id = R.color.darkBlue), shapes.extraLarge)
                .border(2.dp, colorResource(id = R.color.lightBlue), shapes.extraLarge)
                .width(110.dp)
                .height(40.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "Coin",
                        modifier = Modifier.scale(0.6f)
                    )
                    Text(
                        text = AppData.coins.toString(),
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeMainContent(onQuizSelect: () -> Unit = {}) {
    val quizzes = homeViewModel?.state?.availableQuizes
    if(!quizzes.isNullOrEmpty()) {
        LazyColumn {
            items(quizzes) { quiz ->
                HomeQuizCard(quiz = quiz)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun HomeQuizCard(quiz: Quiz, onQuizSelect: () -> Unit = {}) {
    Button(
        onClick = { onQuizSelect() },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(180.dp),
        contentPadding = PaddingValues(0.dp),
        shape = shapes.extraSmall
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    color = Color(quiz.rValue, quiz.gValue, quiz.bValue),
                    shape = shapes.extraSmall
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .matchParentSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = quiz.title,
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.align(alignment = Alignment.Start)
                )
                Text(
                    text = "${quiz.rating} / 100",
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.align(alignment = Alignment.End)
                )
            }
        }
    }
}

@Composable
fun HomeBottomNav(onShopClick: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth().height(200.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .background(
                        color = colorResource(id = R.color.darkBlue),
                        shape = RoundedCornerShape(32.dp, 0.dp, 0.dp, 32.dp)
                    ),
                containerColor = colorResource(id = R.color.darkBlue),
                contentColor = colorResource(id = R.color.darkBlue)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.lightBlue),
                            shape = shapes.extraLarge
                        )
                        .width(100.dp)
                        .height(60.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home",
                        colorFilter = ColorFilter.tint(
                            colorResource(id = R.color.white)
                        )
                    )
                }
            }

            Column {
                Spacer(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.darkBlue))
                        .width(5.dp)
                        .height(10.dp)
                )
                Spacer(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.white))
                        .width(5.dp)
                        .height(60.dp)
                )
                Spacer(
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.darkBlue))
                        .width(5.dp)
                        .height(10.dp)
                )
            }

            FloatingActionButton(
                onClick = { onShopClick() },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .background(
                        color = colorResource(id = R.color.darkBlue),
                        shape = RoundedCornerShape(0.dp, 32.dp, 32.dp, 0.dp)
                    ),
                containerColor = colorResource(id = R.color.darkBlue),
                contentColor = colorResource(id = R.color.darkBlue)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "Shop",
                    colorFilter = ColorFilter.tint(
                        colorResource(id = R.color.white)
                    ),
                    modifier = Modifier.scale(0.75f)
                )
            }
        }
    }
}