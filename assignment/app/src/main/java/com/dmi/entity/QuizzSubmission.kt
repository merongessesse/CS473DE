package com.dmi.entity

import androidx.room.Relation
import androidx.room.Embedded


class QuizzSubmission (
    @Embedded val question: Question,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val submission: Submission
)
