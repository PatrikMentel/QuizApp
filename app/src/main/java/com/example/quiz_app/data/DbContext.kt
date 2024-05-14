package com.example.quiz_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quiz::class, Question::class], version = 1, exportSchema = false)
abstract class DbContext: RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: DbContext? = null

        fun getDatabase(context: Context): DbContext{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbContext::class.java,
                    "db_context"
                ).createFromAsset("database/db_context.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}