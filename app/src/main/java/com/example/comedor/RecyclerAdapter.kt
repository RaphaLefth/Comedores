package com.example.comedor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter( var mNumItem : Int) :
    RecyclerView.Adapter<RecyclerAdapter.numeroViewHolder>() {


    class numeroViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val txt = itemView.findViewById(R.id.txvListNum) as TextView
            fun bind(id : Int){
                txt.text = id.toString()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): numeroViewHolder {

        //se crea el viewholder
        var context : Context = parent.context
        var layoutIdParaListItem : Int = R.layout.grid_layout_list_item

        var attachParentFast = false
        var layoutInflater = LayoutInflater.from(context)
            .inflate(layoutIdParaListItem,parent,attachParentFast)

        return numeroViewHolder(layoutInflater)


    }

    override fun getItemCount(): Int {
        return mNumItem
    }

    override fun onBindViewHolder(holder: numeroViewHolder, position: Int) {
        holder.bind(position)
    }


}