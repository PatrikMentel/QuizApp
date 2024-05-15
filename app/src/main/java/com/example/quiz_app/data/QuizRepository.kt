package com.example.quiz_app.data

import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {

    val availableQuizes: Flow<List<Quiz>> = quizDao.getAvailableQuizes()
    val lockedQuizes: Flow<List<Quiz>> = quizDao.getLockedQuizes()

    suspend fun buyQuiz(quizId: Int) {
        quizDao.buyQuiz(quizId)
    }

    suspend fun completeQuiz(quizId: Int, rating: String) {
        quizDao.completeQuiz(quizId, rating)
    }

}