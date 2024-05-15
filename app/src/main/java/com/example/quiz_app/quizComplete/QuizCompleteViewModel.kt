package com.example.quiz_app.quizComplete

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.AppData
import com.example.quiz_app.data.QuizRepository
import com.example.quiz_app.data.Repositories
import kotlinx.coroutines.launch

class QuizCompleteViewModel(private val quizRepos: QuizRepository = Repositories.quizRepos): ViewModel() {

    fun completeQuiz(sharedPreferences: SharedPreferences) {
        viewModelScope.launch {
            quizRepos.completeQuiz(AppData.chosenQuizId, AppData.points.toString())
        }
        AppData.chosenQuizId = -1
        AppData.coins += (AppData.points * 3f).toInt()
        AppData.points = 0f
        val editor = sharedPreferences.edit()
        editor.putString("coins", AppData.coins.toString())
        editor.apply()
    }

}