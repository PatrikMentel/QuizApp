package com.example.quiz_app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    // Prida novu otazku do databazy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)

    // Ziska vsetky otazky pre dany kviz
    @Query("SELECT * FROM questions WHERE quizId = :chosenQuizId ORDER BY qText")
    fun getQuestionsFor(chosenQuizId: Int): Flow<List<Question>>
}