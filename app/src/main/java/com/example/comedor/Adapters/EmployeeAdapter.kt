package com.example.comedor.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.comedor.Models.Employees

class EmployeeAdapter(val context: Context,
val list : List<Employees>) :BaseAdapter(){
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }
}