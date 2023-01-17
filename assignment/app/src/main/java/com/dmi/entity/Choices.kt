package com.dmi.entity

import androidx.room.Entity
import java.io.Serializable
import androidx.room.PrimaryKey

@Entity()
data class Choice (val questionId: Int, val choice: String): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}