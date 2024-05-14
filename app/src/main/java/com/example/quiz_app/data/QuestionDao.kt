package com.example.quiz_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)

    @Query("SELECT * FROM questions WHERE quizId = :chosenQuizId ORDER BY quizId ASC")
    fun getQuestionsFor(chosenQuizId: Int): Flow<List<Question>>
}