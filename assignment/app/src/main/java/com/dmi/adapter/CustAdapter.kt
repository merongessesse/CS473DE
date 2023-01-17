package com.dmi.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmi.activity.R
import com.dmi.helpers.AnalysisHelper

class CustAdapter(val context: Context, val analysisData: MutableList<AnalysisHelper>) :
        RecyclerView.Adapter<CustViewHolder?>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustViewHolder {
            val view: View = LayoutInflater.from(viewGroup.context)
                .inflate(
                    R.layout.analyis_item_view, viewGroup, false)
            return AssignedTasksViewHolder(view)
        }

        override fun onBindViewHolder(baseViewHolder: CustViewHolder, i: Int) {
            baseViewHolder.onBind(i)
        }


        override fun getItemCount(): Int = analysisData.size



        inner class AssignedTasksViewHolder(view: View?) : CustViewHolder(view!!) {
            var question: TextView? = view?.findViewById(R.id.question)
            var response: TextView? = view?.findViewById(R.id.response)
            var answer: TextView? = view?.findViewById(R.id.correctanswer)
            var container: LinearLayout? = view?.findViewById(R.id.item_container)
            override fun onBind(position: Int) {
                super.onBind(position)
                val data = analysisData[position]

//                productImage?.setBackgroundResource(product.productImage)
                question?.text ="Question " +data.questionId+". "+data.question
                response?.text ="Your response: " +data.response
                response?.setTextColor(Color.parseColor("#d32f2f"))
                Log.d("QUIZZ-DATA","Correct: "+data.correct)
                if(data.correct==1){
                    Log.d("QUIZZ-DATA-INS","Correct: "+ (data.correct==1))
                    response?.setTextColor(Color.parseColor("#388E3C"))
                }
                answer?.text ="Correct answer: " +data.correctAnswer

//                container?.setOnClickListener {
//                    notifyDataSetChanged()
//                }
            }
        }
    }