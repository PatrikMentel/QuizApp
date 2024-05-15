package com.example.quiz_app.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.AppData
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.data.QuizRepository
import com.example.quiz_app.data.Repositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class ShopState(
    val lockedQuizes: List<Quiz> = emptyList()
)

class ShopViewModel(private val quizRepos: QuizRepository = Repositories.quizRepos): ViewModel() {

    var state by mutableStateOf(ShopState())
        private set

    var coinsDisplay = mutableIntStateOf(AppData.coins)

    init {
        getQuizes()
    }

    private fun getQuizes() {
        viewModelScope.launch {
            quizRepos.lockedQuizes.collectLatest {
                state = state.copy(
                    lockedQuizes = it
                )
            }
        }
    }

    fun buyQuiz(quizId: Int, quizAmount: Int, coroutineScope:CoroutineScope): Boolean {
        if (AppData.coins >= quizAmount) {
            AppData.coins -= quizAmount
            coinsDisplay.intValue -= quizAmount
            coroutineScope.launch {
                quizRepos.buyQuiz(quizId)
            }
            return true
        }
        else {
            return false
        }
    }
}