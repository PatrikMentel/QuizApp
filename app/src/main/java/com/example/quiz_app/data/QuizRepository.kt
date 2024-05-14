package com.example.quiz_app.data

import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {

    val availableQuizes: Flow<List<Quiz>> = quizDao.getAvailableQuizes()
    val lockedQuizes: Flow<List<Quiz>> = quizDao.getLockedQuizes()

    fun buyQuiz(quizId: Int): Boolean {
        return quizDao.buyQuiz(quizId)
    }

}