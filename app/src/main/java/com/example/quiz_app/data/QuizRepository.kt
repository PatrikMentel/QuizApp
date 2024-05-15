package com.example.quiz_app.data

import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {

    val availableQuizes: Flow<List<Quiz>> = quizDao.getAvailableQuizes()    // odomknute kvizy
    val lockedQuizes: Flow<List<Quiz>> = quizDao.getLockedQuizes()          // zamknute kvizy


    // kupenie kvizu
    suspend fun buyQuiz(quizId: Int) {
        quizDao.buyQuiz(quizId)
    }

    // dokoncenie kvizu
    suspend fun completeQuiz(quizId: Int, rating: String) {
        quizDao.completeQuiz(quizId, rating)
    }

}