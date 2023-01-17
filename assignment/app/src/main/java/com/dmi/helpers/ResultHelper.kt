package com.dmi.helpers

import java.io.Serializable

data class ResultHelper(val totalQuestions:Int,val correctQuestions:Int, val wrongQuestion:Int, val score:Int):Serializable {
    
}