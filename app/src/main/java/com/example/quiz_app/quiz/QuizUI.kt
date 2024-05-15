package com.example.quiz_app.quiz

import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz_app.R
import com.example.quiz_app.userName.shapes

@Composable
fun QuizScreen(onQuizComplete: () -> Unit = {}) {
    val quizViewModel: QuizViewModel = viewModel(modelClass = QuizViewModel::class.java)
    quizViewModel.onQuizCompleteFunc = onQuizComplete

    if (quizViewModel.quizState.quizQuestions.isEmpty())
    {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Spinner(LocalContext.current)
        }
    }
    else {
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
                        text = quizViewModel.timeRemaing.intValue.toString(),
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
                        .background(colorResource(R.color.darkBlue), shapes.small)
                        .border(2.dp, colorResource(id = R.color.lightBlue), shapes.small)
                        .fillMaxWidth(0.9f)
                        .height(250.dp)
                        .padding(15.dp)
                ) {
                    Text(
                        text = quizViewModel.quizState.quizQuestions[quizViewModel.currIndex.intValue].qText,
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
                    .padding(10.dp, 10.dp, 10.dp, 30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    AnswerButton(
                        index = 0,
                        quizViewModel,
                        answerTxt = quizViewModel.quizState.quizQuestions[quizViewModel.currIndex.intValue].answer1
                    )
                    AnswerButton(
                        index = 1,
                        quizViewModel,
                        answerTxt = quizViewModel.quizState.quizQuestions[quizViewModel.currIndex.intValue].answer2
                    )
                    AnswerButton(
                        index = 2,
                        quizViewModel,
                        answerTxt = quizViewModel.quizState.quizQuestions[quizViewModel.currIndex.intValue].answer3
                    )
                    AnswerButton(
                        index = 3,
                        quizViewModel,
                        answerTxt = quizViewModel.quizState.quizQuestions[quizViewModel.currIndex.intValue].answer4
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerButton(index:Int, quizViewModel: QuizViewModel, answerTxt: String) {
    Button(
        onClick = { quizViewModel.checkAndNext(index) },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue)),
        shape = shapes.extraSmall,
        modifier = Modifier
            .background(colorResource(R.color.darkBlue), shapes.extraSmall)
            .border(2.dp, colorResource(id = R.color.lightBlue), shapes.extraSmall)
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = answerTxt,
            color = colorResource(id = R.color.white),
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
            softWrap = true
        )
    }
    Spacer(modifier = Modifier.width(20.dp))
}