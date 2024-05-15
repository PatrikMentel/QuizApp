package com.example.quiz_app.data

import android.content.Context

object Repositories {
    lateinit var dbContext: DbContext   // inštancia triedy DbContext
        private set

    // poskytnutie repozitara kvizov
    val quizRepos by lazy {
        QuizRepository(dbContext.quizDao())
    }

    // poskytnutie repozitara otazok
    val questionRepos by lazy {
        QuestionRepository(dbContext.questionDao())
    }

    // Ziskanie datavázy
    fun provideContext(context: Context) {
        dbContext = DbContext.getDatabase(context)
    }
}