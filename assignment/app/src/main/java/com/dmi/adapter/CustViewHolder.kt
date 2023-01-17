package com.dmi.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustViewHolder(view: View): RecyclerView.ViewHolder(
    view!!
) {
//    
    var currentPosition = 0
    open fun onBind(position: Int) {
        currentPosition = position
    }
}