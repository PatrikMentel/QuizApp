package com.example.quiz_app

data class AppData(
    var userName: String = "",
    var coins: Int = 100,
    var chosenQuizId: Int = -1,
    var chosenMode: String = ""
)
