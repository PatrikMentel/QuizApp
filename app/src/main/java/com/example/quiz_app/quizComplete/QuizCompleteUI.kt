package com.example.quiz_app.quizComplete

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz_app.AppData
import com.example.quiz_app.R

@Composable
fun QuizCompleteScreen(onContinueClick: () -> Unit = {}) {
    val quizCompleteViewModel = viewModel(modelClass = QuizCompleteViewModel::class.java)
    val shPrefs = LocalContext.current.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gold_trophy),
            contentDescription = "Trophy",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.5f)
        )
        Text(
            text = "Congratulations!",
            textAlign = TextAlign.Center,
            fontSize = 44.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = AppData.points.toString(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight(400)
        )
        Button(
            onClick = {
                quizCompleteViewModel.completeQuiz(shPrefs)
                onContinueClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
        ) {
            Text(
                text = "Continue",
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                fontWeight = FontWeight(400)
            )
        }
    }
}