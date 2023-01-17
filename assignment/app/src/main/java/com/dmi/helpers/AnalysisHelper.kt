package com.dmi.helpers

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable



@Parcelize
 data class AnalysisHelper(val questionId: Int, val question: String, val response:String, val correctAnswer: String, val correct:Int): Parcelable {
}