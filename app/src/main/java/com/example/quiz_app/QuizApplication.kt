package com.example.quiz_app

import android.app.Application
import com.example.quiz_app.data.Repositories

// trieda aplikacie, poskytuje kontext repozitarom
class QuizApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Repositories.provideContext(this)
    }
}