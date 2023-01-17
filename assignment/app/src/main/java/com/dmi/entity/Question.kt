package com.dmi.entity

import androidx.room.Entity
import java.io.Serializable
import androidx.room.PrimaryKey

@Entity()
data class Question (val description: String, val multi_answer: Int, val quizId: Int):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}
