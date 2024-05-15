package com.example.quiz_app.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Query("UPDATE Quizes SET rating = :rating WHERE id = :quizId")
    suspend fun completeQuiz(quizId: Int, rating: String)

    @Query("SELECT * FROM Quizes WHERE locked = 0 ORDER BY id ASC")
    fun getAvailableQuizes(): Flow<List<Quiz>>

    @Query("SELECT * FROM Quizes WHERE locked = 1 ORDER BY id ASC")
    fun getLockedQuizes(): Flow<List<Quiz>>

    @Query("UPDATE Quizes SET locked = 0 WHERE id = :quizId")
    suspend fun buyQuiz(quizId: Int)
}