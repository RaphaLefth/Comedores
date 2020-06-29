package com.example.comedor.Holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.R

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var icons = itemView.findViewById<ImageView>(R.id.icon_image_view)!!
    var titles = itemView.findViewById<TextView>(R.id.title_text_view)!!
}