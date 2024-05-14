package com.example.quiz_app.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "Questions", primaryKeys = ["quizId", "qText"], foreignKeys = [
    ForeignKey(entity = Quiz::class, parentColumns = ["id"], childColumns = ["quizId"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class Question(
    val quizId: Int,
    val qText: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val correctAnswer: Int
)
