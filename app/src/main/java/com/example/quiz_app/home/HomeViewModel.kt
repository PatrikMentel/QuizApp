package com.example.quiz_app.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.data.Quiz
import com.example.quiz_app.data.QuizRepository
import com.example.quiz_app.data.Repositories
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// datova trieda zoznamu dostupnych kvizov
data class HomeState(
    val availableQuizes: List<Quiz> = emptyList()
)

// uklada stav domovskej obrazovke a berie kvizy z databazy
class HomeViewModel(private val quizRepos: QuizRepository = Repositories.quizRepos): ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        getQuizes()
    }

    // pomocou korutiny nacita kvizy
    private fun getQuizes() {
        viewModelScope.launch {
            quizRepos.availableQuizes.collectLatest {
                homeState = homeState.copy(
                    availableQuizes = it
                )
            }
        }
    }
}