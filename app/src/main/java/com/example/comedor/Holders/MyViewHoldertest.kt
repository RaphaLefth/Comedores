package com.example.comedor.Holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Models.ComedorModelData

class MyViewHoldertest(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var currentUserlist: ComedorModelData? = null
    var currentPosition: Int = 0
}