package com.dmi.entity

import androidx.room.PrimaryKey
import androidx.room.Entity
import java.io.Serializable
@Entity()
data class Quizz (val name: String, val description: String, val active: Int): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
}