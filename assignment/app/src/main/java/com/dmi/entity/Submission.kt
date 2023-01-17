package com.dmi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalTime
import java.time.LocalDate


@Entity
data class Submission (
    val questionId: Int,
    val quizId: Int,
    var answer: String,
    var answerCorrect: Int,
    val correctAnswer: String,
    var pctCorrect: Double,
    val submittedBy: String,
    val submitDate: String,
    val submitTime: String,
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}