package com.example.quiz_app.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quiz_app.AppData
import com.example.quiz_app.R
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.userName.shapes

@Composable
fun ShopScreen(onHomeClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        ShopTopBar()
        Spacer(modifier = Modifier.padding(10.dp))
        ShopMainContent()
        ShopBottomNav(onHomeClick)
    }
}

@Composable
fun ShopTopBar() {
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
                .background(Color(R.color.darkBlue), shapes.extraLarge)
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
fun ShopMainContent() {
    val quizzes = mutableListOf<Quiz>()
    val q1 = Quiz(0, "General", 0,0,0, "0", 100, 0)
    val q2 = Quiz(1, "History", 0,0,0, "0", 120, 0)
    val q3 = Quiz(2, "Science & Tech", 0,0,0, "0", 120, 0)
    val q4 = Quiz(3, "Hobbies", 0,0,0, "0", 100, 0)
    val q5 = Quiz(4, "Monuments", 0,0,0, "0", 80, 0)
    quizzes.add(q1)
    quizzes.add(q2)
    quizzes.add(q3)
    quizzes.add(q4)
    quizzes.add(q5)
    LazyColumn() {
        items(quizzes) { quiz ->
            ShopQuizCard(quiz = quiz)
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun ShopQuizCard(quiz: Quiz) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(180.dp)
            .background(color = Color(quiz.rValue, quiz.gValue, quiz.bValue), shape = shapes.extraSmall)
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
            Box {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.coin),
                                contentDescription = "Coin",
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = quiz.buyAmount.toString(),
                                color = colorResource(id = R.color.white),
                                fontSize = 28.sp,
                                fontWeight = FontWeight(500),
                                softWrap = true
                            )
                        }
                    }
                    Box {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
                        ) {
                            Text(
                                text = "BUY",
                                color = colorResource(id = R.color.white),
                                fontSize = 20.sp,
                                fontWeight = FontWeight(500)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShopBottomNav(onHomeClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier.shadow(20.dp, shapes.extraLarge)
        ) {
            Button(
                onClick = { onHomeClick() },
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .background(
                        color = colorResource(id = R.color.darkBlue),
                        shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
            ) {
                Image(painter = painterResource(id = R.drawable.home), contentDescription = "Home", colorFilter = ColorFilter.tint(
                    colorResource(id = R.color.white)))
            }

            Column {
                Spacer(modifier = Modifier
                    .background(color = colorResource(id = R.color.darkBlue))
                    .width(5.dp)
                    .height(10.dp))
                Spacer(modifier = Modifier
                    .background(color = colorResource(id = R.color.white))
                    .width(5.dp)
                    .height(60.dp))
                Spacer(modifier = Modifier
                    .background(color = colorResource(id = R.color.darkBlue))
                    .width(5.dp)
                    .height(10.dp))
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .background(
                        color = colorResource(id = R.color.darkBlue),
                        shape = RoundedCornerShape(topEnd = 40.dp, bottomEnd = 40.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
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
                    Image(painter = painterResource(id = R.drawable.shop), contentDescription = "Shop", colorFilter = ColorFilter.tint(
                        colorResource(id = R.color.white)))
                }
            }
        }
    }
}