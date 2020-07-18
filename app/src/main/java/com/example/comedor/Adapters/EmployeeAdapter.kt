package com.example.comedor.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Models.Employees
import com.example.comedor.R

class EmployeeAdapter(val context: Context,
                      private val list : List<Employees>) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val employeeId = view.findViewById<TextView>(R.id.employeeId)!!
        val employeeDni = view.findViewById<TextView>(R.id.employeeDNI)!!
        val employeeName = view.findViewById<TextView>(R.id.employeeName)!!
        val employeeLastName = view.findViewById<TextView>(R.id.employeeLastName) !!
        val employeeCategory = view.findViewById<TextView>(R.id.employeeCategory)!!
        val employeeIdEmpresa = view.findViewById<TextView>(R.id.employeeIdEmpresa)!!
        val employeeStatus = view.findViewById<TextView>(R.id.employeeStatus)!!



    }
//listview
/*
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view : View = LayoutInflater.from(context).inflate(R.layout.row_layout_empleados,parent,false)
//        val employeeId = view.findViewById(R.id.employeeId) as AppCompatTextView
//        val employeeDni = view.findViewById(R.id.employeeDNI) as AppCompatTextView
//        val employeeName = view.findViewById(R.id.employeeName) as AppCompatTextView
//        val employeeLastName = view.findViewById(R.id.employeeLastName) as AppCompatTextView
//        val employeeCategory = view.findViewById(R.id.employeeCategory) as AppCompatTextView
//        val employeeIdEmpresa = view.findViewById(R.id.employeeIdEmpresa) as AppCompatTextView
//        val employeeStatus = view.findViewById(R.id.employeeStatus) as AppCompatTextView
    //  val employeeDateAdmission = view.findViewById(R.id.employeeDateAdmission) as AppCompatTextView
    //  val employeeDateSus = view.findViewById(R.id.employeeDateSus) as AppCompatTextView
//
//        employeeId.text = list[position].id.toString()
//        employeeDni.text = list[position].dni.toString()
//        employeeName.text = list[position].nombre.toString()
//        employeeLastName.text = list[position].apellido.toString()
//        employeeCategory.text = list[position].categoria.toString()
//        employeeIdEmpresa.text = list[position].id_empresa.toString()
//        employeeStatus.text = list[position].estado.toString()
//      //  employeeDateAdmission.text = list[position].fecha_ingreso.toString()
//        //employeeDateSus.text = list[position].fecha_cese.toString()
//        return view
//    }

//    override fun getItem(position: Int): Any {
//        return list[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return  list.size
//    }

*/
    //recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.grid_view_layout_empleados,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemEmployee: Employees = list[position]
        holder.apply {
            employeeName.text = itemEmployee.nombre
            employeeDni.text = itemEmployee.dni
            employeeLastName.text = itemEmployee.apellido
            employeeCategory.text = itemEmployee.id_categoria.toString()
            employeeIdEmpresa.text = itemEmployee.ruc_empresa
            employeeStatus.text = itemEmployee.estado.toString()
        }
//        holder.itemView.setOnClickListener()
    }
}