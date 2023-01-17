package com.dmi.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dmi.activity.R
import com.dmi.activity.databinding.FragmentHomeBinding
import com.dmi.db.QuizDatabase
import com.dmi.entity.*
import com.dmi.helpers.QuizHelper
import com.dmi.helpers.ResultHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.roundToInt

class HomeFragment : BaseFragment() {
    private var allQuestions= ArrayList<QuestionChoice>()
    private var currentIndex =0
    private var maxItems:Int? = 0
    private var quizId = 0
     var username:String ="Test"
    lateinit var binding: FragmentHomeBinding
    var userActivity = ArrayList<QuizHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            var user = it.getString("username")
            if(user !=null){
                username = user!!
            }
            binding.title.setText("User: "+username)
        }
        launch {
            context?.let {
                var db1 = QuizDatabase(it, CoroutineScope(Dispatchers.IO))
                allQuestions.addAll(db1.getQuizzDao().getQuestionsWithChoices())
                quizId = allQuestions.first().question.quizId
                Log.d("QUIZZ-SIZE", allQuestions?.first().choices.first().choice)
            }
            

            if (allQuestions.size > 0) {
                maxItems = allQuestions?.size
                var currentQuestion = allQuestions?.get(currentIndex)
                showNextQuestion(currentQuestion)
                binding.nextBtn.setOnClickListener { nextClickEvent() }
                binding.prevBtn.setOnClickListener { prevClickEvent() }
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

   private fun nextClickEvent(){
       var currentQuestion = allQuestions?.get(currentIndex)
        val checkedIndex = binding.radioGroup.checkedRadioButtonId
       if(checkedIndex==-1){
           var toast = Toast.makeText(context, "You must select one option", Toast.LENGTH_LONG)
           toast.setGravity(Gravity.TOP or Gravity.LEFT, 50, 0)
           toast.show()

           return
       }
       Log.d("QUIZZ",checkedIndex.toString())
       val rb = binding.radioGroup.findViewById(checkedIndex) as RadioButton
       var existing = userActivity.filter { s -> s.questionId == currentQuestion?.question?.id  }
       if(existing.size ==0){
           userActivity.add(QuizHelper(currentQuestion?.question?.id,rb.text.toString(),checkedIndex))
       }
       else{
           userActivity.set( userActivity.indexOf(existing.first()),QuizHelper(currentQuestion?.question?.id,rb.text.toString(),checkedIndex))
       }
       Log.d("QUIZZ-PREV-ANSWER",userActivity.filter { s -> s.questionId == currentQuestion?.question?.id  }.single().userResponse)
       if(currentIndex+1 <maxItems!!){
           currentIndex++
           binding.radioGroup.clearCheck()
           binding.radioGroup.removeAllViews()
            currentQuestion = allQuestions?.get(currentIndex)
           showNextQuestion(currentQuestion)
       }
       else{
           Toast.makeText(context, "Please wait, you will transfered to the result page",Toast.LENGTH_LONG).show()
           processResult()
       }

   }
    private fun prevClickEvent(){
       if(binding.nextBtn.text.toString().lowercase().contains("result")){
           binding.nextBtn.setText("Next")
       }
        if(currentIndex ==1){
            binding.prevBtn.isEnabled = false
            binding.prevBtn.isClickable = false
        }
        currentIndex--

        binding.radioGroup.clearCheck()
        binding.radioGroup.removeAllViews()
        var currentQuestion = allQuestions?.get(currentIndex)
        var existing = userActivity.filter { s -> s.questionId == currentQuestion?.question?.id  }
         Log.d("QUIZZ-PREV-ANSWER",userActivity.filter { s -> s.questionId == currentQuestion?.question?.id  }.single().userResponse)
        showNextQuestion(currentQuestion)

    }

    @SuppressLint("ResourceAsColor")
    private fun showNextQuestion(currentQuestion: QuestionChoice?){
        if(currentQuestion !=null){

            binding.questionTxt.setText(currentQuestion.question.id.toString()+". "+currentQuestion.question.description)

            var choices = currentQuestion.choices
            for(choice in choices){
              //  var rButton = RadioButton(context)
               var rButton = AppCompatRadioButton(context);
                rButton.text = choice.choice
                var layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0,10,0,0)
                layoutParams.weight=1F
                rButton.setTextColor(getResources().getColor(R.color.mater_grey_700));

                rButton.layoutParams = layoutParams

                rButton.id = View.generateViewId()
                rButton.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
                binding.radioGroup.addView(rButton)

            }

            if((currentIndex+1) == maxItems!!){
                binding.nextBtn.setText("View Result")
            }

            if(currentIndex >0){
                binding.prevBtn.isEnabled = true
                binding.prevBtn.isClickable = true
            }

        }
    }
    private fun processResult(){
        binding.nextBtn.isEnabled = false
        binding.prevBtn.isEnabled = false
        binding.prevBtn.isClickable = false
        binding.nextBtn.isClickable = false

        var listOfResponse = ArrayList<Submission> ()
        launch {
            context?.let {
                var db = QuizDatabase(it,CoroutineScope(Dispatchers.Main))
                var correctAnswers= db.getQuizzDao().getAnswers()
                for(answer in correctAnswers){
                    var submission = Submission(answer.questionId,quizId,"",0,
                        answer.answer,0.0,"Student1", LocalDate.now().toString(),LocalTime.now().toString())
                    var userResponse = userActivity.filter { s -> s.questionId == answer.questionId  }
                    if(userResponse.size >0){
                        var userChoice = userResponse.first().userResponse;
                        submission.answer = userChoice
                        if(userChoice.trim().equals(answer.answer.trim())){
                            submission.answerCorrect =1
                            }
                    }
                    listOfResponse.add(submission)
                }
                var arrayResponse = listOfResponse.toTypedArray()
                db.getQuizzDao().deleteByName(quizId,"Student1")
                db.getQuizzDao().submitAnswers(*arrayResponse)
                showToResult(db.getQuizzDao().getSubmissionByName("Student1",1))

            }
        }


    }
    private fun showToResult(answers: List<Submission>){
        var totalQuestions = answers.size.toFloat();
        var correctResponse = answers.filter { it->it.answerCorrect ==1 }.size.toFloat()
        var score = ((correctResponse/totalQuestions)*100).roundToInt()
        Log.d("QUIZZ-RESULT","Qns: "+totalQuestions+", Correct: "+correctResponse+", Score: "+score+"%")
        var resultHelper = ResultHelper(totalQuestions.toInt(),correctResponse.toInt(),(totalQuestions-correctResponse).toInt(),score)
        val action = HomeFragmentDirections.homefragResultfragAction(resultHelper, username)
        Log.d("QUIZZ-RESULT","Navigating to result with "+resultHelper.score)
        Thread.sleep(5_000)
        Navigation.findNavController(binding.root).navigate(action)
    }
}