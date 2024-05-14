package com.example.quiz_app.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz_app.R
import com.example.quiz_app.userName.shapes

@Composable
fun QuizScreen(onQuizComplete: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                Text(
                    text = "0:56",
                    color = colorResource(id = R.color.white),
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .background(Color(R.color.darkBlue), shapes.small)
                    .border(2.dp, colorResource(id = R.color.lightBlue), shapes.small)
                    .fillMaxWidth(0.9f)
                    .height(250.dp)
                    .padding(15.dp)
            ) {
                Text(
                    text = "What is difference between backend and frontend in web development?",
                    color = colorResource(id = R.color.white),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp,
                    lineHeight = 30.sp
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp,10.dp,10.dp,30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                AnswerButton(answerTxt = "A: Answer1")
                AnswerButton(answerTxt = "B: Answer2")
                AnswerButton(answerTxt = "C: Answer3")
                AnswerButton(answerTxt = "D: Answer4")
            }
        }
    }
}

@Composable
fun AnswerButton(answerTxt: String) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue)),
        shape = shapes.extraSmall,
        modifier = Modifier
            .background(Color(R.color.darkBlue), shapes.extraSmall)
            .border(2.dp, colorResource(id = R.color.lightBlue), shapes.extraSmall)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = answerTxt,
            color = colorResource(id = R.color.white),
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
            softWrap = true
        )
    }
    Spacer(modifier = Modifier.width(20.dp))
}