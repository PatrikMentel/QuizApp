package com.example.quiz_app.quiz

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_app.AppData
import com.example.quiz_app.data.Question
import com.example.quiz_app.data.QuestionRepository
import com.example.quiz_app.data.Repositories
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class QuizState(
    val quizQuestions: List<Question> = emptyList()
)

class QuizViewModel(private val questionRepos: QuestionRepository = Repositories.questionRepos): ViewModel() {

    var quizState by mutableStateOf(QuizState())
        private set

    private val timer = object: CountDownTimer(12000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timeRemaing.intValue--
        }

        override fun onFinish() {
            finishQuiz()
        }
    }
    var timeRemaing = mutableIntStateOf(12)
    var currIndex = mutableIntStateOf(0)
    private var totalPoints = 0f
    var onQuizCompleteFunc: () -> Unit = {}

    init {
        timer.start()
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            questionRepos.getQuestionsFor(AppData.chosenQuizId).collectLatest {
                quizState = quizState.copy(
                    quizQuestions = it
                )
            }
        }
    }

    fun checkAndNext(answerIndex: Int) {
        if (answerIndex == quizState.quizQuestions[currIndex.intValue].correctAnswer) {
            totalPoints += 10f
        }

        if (currIndex.intValue < (quizState.quizQuestions.size - 1)) {
            currIndex.intValue++
        }
        else {
            finishQuiz(onQuizCompleteFunc)
        }
    }

    private fun finishQuiz(onQuizComplete: () -> Unit = {}) {
        AppData.points = totalPoints
        timer.cancel()
        onQuizCompleteFunc()
    }
}