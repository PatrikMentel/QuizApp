package com.example.quiz_app.data

import android.content.Context

object Repositories {
    lateinit var dbContext: DbContext
        private set

    val quizRepos by lazy {
        QuizRepository(dbContext.quizDao())
    }

    val questionRepos by lazy {
        QuestionRepository(dbContext.questionDao())
    }

    fun provideContext(context: Context) {
        dbContext = DbContext.getDatabase(context)
    }
}