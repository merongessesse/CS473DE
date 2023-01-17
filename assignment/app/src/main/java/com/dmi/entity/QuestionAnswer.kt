package com.dmi.entity

import androidx.room.Relation
import androidx.room.Embedded


data class QuestionAnswer (
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val answer: Answer
    )