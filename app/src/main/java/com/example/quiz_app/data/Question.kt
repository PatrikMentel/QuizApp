package com.example.quiz_app.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "Questions", primaryKeys = ["quizId", "qText"], foreignKeys = [
    ForeignKey(entity = Quiz::class, parentColumns = ["id"], childColumns = ["quizId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class Question(
    val quizId: Int,        // identifikacne cislo kvizu z tabulky quizes
    val qText: String,      // text otazky
    val answer1: String,    // odpoved 1
    val answer2: String,    // odpoved 2
    val answer3: String,    // odpoved 3
    val answer4: String,    // odpoved 4
    val correctAnswer: Int  // hodnota urcujuca spravnu odpoved
)
