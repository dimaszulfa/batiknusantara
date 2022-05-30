package com.dimaszulfa.batiknusantara.data

data class QuizEntity(
    var quizQuestion: String? = null,
    var optionOne: String?= null,
    var optionTwo: String?= null,
    var optionThree: String?= null,
    var optionFour: String?= null,
    var correctAnswer: String?= null
)
