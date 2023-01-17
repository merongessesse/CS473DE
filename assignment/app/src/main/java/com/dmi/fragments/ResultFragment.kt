package com.dmi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dmi.activity.databinding.FragmentResultBinding
import com.dmi.db.QuizDatabase
import com.dmi.helpers.AnalysisHelper
import com.dmi.helpers.ResultHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultFragment : BaseFragment() {
    lateinit var binding: FragmentResultBinding
    var username: String = "Test"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            var data = it.getSerializable("quizresult") as ResultHelper?
            var user = it.getString("username")
            if(user !=null){
                username = user!!
            }
            binding.correctanswers.setText(data?.correctQuestions.toString())
            binding.totalQuestion.setText(data?.totalQuestions.toString())
            binding.scoreTxt.setText(data?.score.toString()+"%")
            binding.btnAnalysis.setOnClickListener { processingResultAnalysis() }
            binding.retakeBtn.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(ResultFragmentDirections.resultfragHomefragAction(username))
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }
 private fun processingResultAnalysis(){
     launch {
         context?.let {
             var db = QuizDatabase(it, CoroutineScope(Dispatchers.Main))
             val questions = db.getQuizzDao().getQuestions()
             var analysisData = ArrayList<AnalysisHelper>()
             var submissions = db.getQuizzDao().getSubmissionByName("Student1",1)
             for(item in submissions){
                 val question = questions.filter { it-> it.id==item.questionId }.first().description
                 analysisData.add(AnalysisHelper(item.questionId,question,item.answer,item.correctAnswer, item.answerCorrect))
             }

             Navigation.findNavController(binding.root).navigate(ResultFragmentDirections.resultfragAnalysisfragAction(analysisData.toTypedArray()))
         }
     }

 }

}