package com.example.comedor.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Models.Comedor
import com.example.comedor.R
import com.example.comedor.View.AdministratorView.AdminComedorInfo
import kotlinx.android.synthetic.main.admincomedorlayout.view.*

class AdminComedorAdapter(val context: Context, val comedorlistData: ArrayList<Comedor>)
    : RecyclerView.Adapter<AdminComedorAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.admincomedorlayout
            , parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return  comedorlistData.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val comedorListdata=comedorlistData[position]
        holder.setData(comedorListdata,position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var currentComedorlist: Comedor? = null
        private var currentPosition: Int = 0

        init {

            itemView.setOnClickListener{
                val i = Intent(context, AdminComedorInfo::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val infoPos = comedorlistData[currentPosition]

                i.putExtra("getComedorInf",infoPos)
                context.startActivity(i)
//                newAct(currentComedorlist!!.nombre)
            }
        }


        //        init {
//            //delete button
//            itemView.delete_data.setOnClickListener {
//
////                deleteData(currentComedorlist!!.id_comedor)
//                deleteComedor(currentComedorlist!!.id_comedor)
//
//                comedorlistData.removeAt(currentPosition)
//
//                notifyDataSetChanged()
//            }
//            //edit button
//            itemView.edite_data.setOnClickListener {
//
//                val i= Intent(context, AdminEditDataComedorActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                val comedorPosition = comedorlistData[currentPosition]
//
//                i.putExtra("getlistData",comedorPosition)
//                context.startActivity(i)
//            }
//
//        }
        fun setData(comedorListData: Comedor?, pos: Int) {


            comedorListData?.let {

                itemView.myname.text = comedorListData.nombre
//                itemView.myemail.text = userlistdataData.administrador
//                itemView.myaddress.text = userlistdataData.ruc


            }
            this.currentComedorlist = comedorListData
            this.currentPosition = pos
        }
    }

}
