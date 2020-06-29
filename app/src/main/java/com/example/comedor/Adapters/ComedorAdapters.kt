package com.example.comedor.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Holders.ItemHolder
import com.example.comedor.Models.ItemBtnGeneric
import com.example.comedor.R
import com.example.comedor.View.ComedorView.*

class ComedorAdapters(var context: Context, private var arrayList: ArrayList<ItemBtnGeneric>) :
RecyclerView.Adapter<ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_layout_comedor_items, parent, false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val itemBtnGeneric: ItemBtnGeneric = arrayList[position]

        holder.icons.setImageResource(itemBtnGeneric.icons!!)

        holder.titles.text = itemBtnGeneric.alpha

        holder.titles.setOnClickListener {

            iconBtn(position)
            Toast.makeText(context, "apretaste $position",Toast.LENGTH_LONG).show()
        }
        holder.icons.setOnClickListener {
            iconBtn(position)
        }

    }

    private fun iconBtn( p : Int) {
        when(p){
            0 -> checkPersonal()
            1 -> checkComedor()
            2 -> seeReports()
            3 -> checkServices()
            4->  addConsume()
        }
    }

    //checked
    private fun addConsume() {
        context.startActivity(Intent(context,AddConsumoView::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    //checked
    private fun checkServices() {
        val intent = Intent(context,ServicesInfoView::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    //checked
    private fun seeReports() {
        context.startActivity(Intent(context,ComedorCheckReports::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    //checked
    private fun checkComedor() {
        context.startActivity(Intent(context,ComedorInfoView::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
    //checked
    private fun checkPersonal() {
        context.startActivity(Intent(context, CheckPersonalView::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}