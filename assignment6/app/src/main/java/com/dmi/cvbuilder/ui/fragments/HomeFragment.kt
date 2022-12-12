package com.dmi.cvbuilder.ui.fragments

import androidx.fragment.app.Fragment
import edu.miu.cvbuilder.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import cvbuilder.R
import android.os.Bundle
import android.view.View


class HomeFragment : Fragment(R.layout.fragment_home){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initUI()
    }

    private fun initUI() {

        val id = R.drawable.profile2
        Utils.setCircleImageToImageView(context, profileImageView, id, 20, R.color.md_white_1000)


        context?.let { Utils.setImageToImageView(it, coverUserImageView, id) }

    }
}