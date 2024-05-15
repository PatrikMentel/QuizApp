package com.example.quiz_app.data

import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {

    // Ziskanie otazok daneho kvizu
    fun getQuestionsFor(chosenQuizId: Int): Flow<List<Question>> {
        return questionDao.getQuestionsFor(chosenQuizId)
    }

}