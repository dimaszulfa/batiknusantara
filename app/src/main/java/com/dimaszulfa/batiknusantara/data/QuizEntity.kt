package com.dimaszulfa.batiknusantara.data

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class QuizEntity(
    var quizQuestion: String? = null,
    var optionOne: String?= null,
    var optionTwo: String?= null,
    var optionThree: String?= null,
    var optionFour: String?= null,
    var correctAnswer: String?= null
): Parcelable
