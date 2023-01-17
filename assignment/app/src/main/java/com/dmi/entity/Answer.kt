package com.dmi.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

import java.io.Serializable

@Entity()
data class Answer(val questionId: Int,val answer: String): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}