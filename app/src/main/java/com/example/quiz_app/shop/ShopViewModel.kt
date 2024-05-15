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

// datova trieda zoznamu zamknutych kvizov
data class ShopState(
    val lockedQuizes: List<Quiz> = emptyList()
)

// uklada stav obrazovky obchodu a berie kvizy z databazy
class ShopViewModel(private val quizRepos: QuizRepository = Repositories.quizRepos): ViewModel() {

    var state by mutableStateOf(ShopState())
        private set

    var coinsDisplay = mutableIntStateOf(AppData.coins) // sluzi na zobrazenie a aktualizovanie hodnoty minci

    init {
        getQuizes()
    }

    // nacita kvizy z databazy
    private fun getQuizes() {
        viewModelScope.launch {
            quizRepos.lockedQuizes.collectLatest {
                state = state.copy(
                    lockedQuizes = it
                )
            }
        }
    }

    // skontroluje pocet minci a podla toho odoberie dany pocet. Nasledne aktualizuje databazu
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