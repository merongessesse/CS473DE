package com.dmi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmi.activity.databinding.FragmentAnalysisBinding
import com.dmi.adapter.CustAdapter
import com.dmi.helpers.AnalysisHelper

class AnalysisFragment : BaseFragment()  {
    lateinit var binding: FragmentAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    arguments?.let {
        var data = it.get("resultanalysis") as Array<AnalysisHelper>
        Log.d("QUIZZ-ANALYSIS", "Passed"+data?.toList().first().correctAnswer)

        val recyclerView = binding.quizRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CustAdapter(requireContext(), data.toMutableList())
        recyclerView.adapter = adapter
    }
}

}