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
import com.example.comedor.View.AdministratorView.*

class AdminAdapter(var context: Context, private var arrayList: ArrayList<ItemBtnGeneric>) :
    RecyclerView.Adapter<ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val viewHolder = LayoutInflater.from(parent.context).
        inflate(R.layout.grid_view_layout_comedor_items,
        parent,false)
        return ItemHolder(viewHolder)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        val itemBtnGeneric : ItemBtnGeneric = arrayList[position]

        holder.icons.setImageResource(itemBtnGeneric.icons!!)
        holder.titles.text = itemBtnGeneric.alpha

        holder.titles.setOnClickListener {

            iconBtn(position)
            Toast.makeText(context, "apretaste $position", Toast.LENGTH_LONG).show()
        }
        holder.icons.setOnClickListener {
            iconBtn(position)
        }

    }

    private fun iconBtn( p : Int) {
        when(p){
            0 -> comedor()
            1 -> personal()
            2 -> usuarios()
            3->  servicios()
        }
    }

    private fun servicios() {
        context.startActivity(Intent(context,AdminServicios::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun usuarios() {
        context.startActivity(Intent(context,AdminUsuarios::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun personal() {
        context.startActivity(Intent(context,AdminEmployess::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }


    private fun comedor() {
        context.startActivity(Intent(context,AdminComedor::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}