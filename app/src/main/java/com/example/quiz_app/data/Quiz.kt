package com.example.quiz_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quizes")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id: Int,    // identifikacne cislo kvizu
    val title: String,  // nazov kvizu
    val rValue: Int,    // hodnota cervenej farby
    val gValue: Int,    // hodnota zelenej farby
    val bValue: Int,    // hodnota modrej farby
    val rating: String, // hodnotenie kvizu
    val buyAmount: Int, // cena kvizu
    val locked: Int     // hodnota urcujuca zamknutie kvizu
)
