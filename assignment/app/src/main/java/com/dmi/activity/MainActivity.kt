package com.dmi.quizzapp

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dmi.activity.R
import com.dmi.activity.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // btnReset.setOnClickListener { resetAnswers() }

       //btnSubmit.setOnClickListener{submitQuizz()}
    }

//    private fun submitQuizz() {
//        var qn1Answer = ""
//        var qn2Answer = ""
//        var radioButton1: RadioButton?= null
//        var radioButton2: RadioButton?= null
//        var score = 0
//        val qn1CheckedIndex = question1_group.checkedRadioButtonId
//        val qn2CheckedIndex = question2_group.checkedRadioButtonId
//        if(qn1CheckedIndex == -1 || qn2CheckedIndex == -1){
//            Toast.makeText(this, "Please amswer all questions",Toast.LENGTH_LONG).show()
//            return
//        }
//        if(qn1CheckedIndex !== -1)
//             radioButton1 = question1_group.findViewById(qn1CheckedIndex) as RadioButton
//        if(qn2CheckedIndex !== -1)
//            radioButton2 = question2_group.findViewById(qn2CheckedIndex) as RadioButton
//
//        if(radioButton1 !=null)
//            qn1Answer = radioButton1.text.toString()
//        if(radioButton2 !=null)
//             qn2Answer = radioButton2.text.toString()
//        if(qn1Answer.contains("True"))
//            score+=50
//        if(qn2Answer.contains("True"))
//            score+=50
//        val msg = "Congratulations! You submitted on date: ${LocalDate.now()} and time: ${LocalTime.now()}. Your achieved $score%"
//          showAlert(msg)
//
//    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quizz submission notification")
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


//    fun resetAnswers() {
//        question1_group.clearCheck()
//        question2_group.clearCheck()
//    }

}
