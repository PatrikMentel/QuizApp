package com.example.quiz_app.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quizes")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val rValue: Int,
    val gValue: Int,
    val bValue: Int,
    val rating: String,
    val buyAmount: Int,
    val locked: Int
)
