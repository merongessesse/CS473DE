package com.dmi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dmi.activity.R
import com.dmi.activity.databinding.FragmentStartBinding


class StartFragment : BaseFragment() {
    lateinit var binding:FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnStartQuiz.setOnClickListener {
            if(binding.username.text.isEmpty()){
                binding.username.error ="Please enter your name"
                binding.username.requestFocus()
                return@setOnClickListener
            }
            var username = binding.username.text.toString()
            Navigation.findNavController(binding.root).navigate(StartFragmentDirections.actionStartFragmentToHomeFragment(username))
        }
    }

    }
