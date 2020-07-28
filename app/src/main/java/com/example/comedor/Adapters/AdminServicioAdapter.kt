package com.example.comedor.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Models.ServiceEmpresa
import com.example.comedor.Models.Servicio
import com.example.comedor.R
import kotlinx.android.synthetic.main.row_servicios_diponibles.view.*

class AdminServicioAdapter(val context : Context, private val arrayList: ArrayList<ServiceEmpresa>) : RecyclerView.Adapter<
        AdminServicioAdapter.MyServiceViewHolder>(){
//    private val comedor_servicio_list : ArrayList<ServiceEmpresa>

    inner class MyServiceViewHolder(itemsServicioView : View) : RecyclerView.ViewHolder(itemsServicioView){

        private var currentService : ServiceEmpresa ?=null
        private var currentPosition : Int = 0


        fun setData(serviceList: ServiceEmpresa, position: Int) {
            serviceList.let {
                itemView.tv_servicio.text = serviceList.descripcion
                itemView.tv_precio.text = serviceList.precio
            }

            this.currentService = serviceList
            this.currentPosition = position
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyServiceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_servicios_diponibles,parent,false)
        return MyServiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    override fun onBindViewHolder(holder: MyServiceViewHolder, position: Int) {
        val serviceList = arrayList[position]
        holder.setData(serviceList,position)
    }


}