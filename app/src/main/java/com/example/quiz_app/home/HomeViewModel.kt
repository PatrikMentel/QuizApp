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

data class HomeState(
    val availableQuizes: List<Quiz> = emptyList()
)

class HomeViewModel(private val quizRepos: QuizRepository = Repositories.quizRepos): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getQuizes()
    }

    private fun getQuizes() {
        viewModelScope.launch {
            quizRepos.availableQuizes.collectLatest {
                state = state.copy(
                    availableQuizes = it
                )
            }
        }
    }
}