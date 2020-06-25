package com.example.comedor.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.example.comedor.Models.Employees
import com.example.comedor.R

class EmployeeAdapter(val context: Context,
val list : List<Employees>) :BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.row_layout_empleados,parent,false)
        val employeeId = view.findViewById(R.id.employeeId) as AppCompatTextView
        val employeeDni = view.findViewById(R.id.employeeDNI) as AppCompatTextView
        val employeeName = view.findViewById(R.id.employeeName) as AppCompatTextView
        val employeeLastName = view.findViewById(R.id.employeeLastName) as AppCompatTextView
        val employeeCategory = view.findViewById(R.id.employeeCategory) as AppCompatTextView
        val employeeIdEmpresa = view.findViewById(R.id.employeeIdEmpresa) as AppCompatTextView
        val employeeStatus = view.findViewById(R.id.employeeStatus) as AppCompatTextView
    //    val employeeDateAdmission = view.findViewById(R.id.employeeDateAdmission) as AppCompatTextView
      //  val employeeDateSus = view.findViewById(R.id.employeeDateSus) as AppCompatTextView

        employeeId.text = list[position].id.toString()
        employeeDni.text = list[position].dni.toString()
        employeeName.text = list[position].nombre.toString()
        employeeLastName.text = list[position].apellido.toString()
        employeeCategory.text = list[position].categoria.toString()
        employeeIdEmpresa.text = list[position].id_empresa.toString()
        employeeStatus.text = list[position].estado.toString()
      //  employeeDateAdmission.text = list[position].fecha_ingreso.toString()
        //employeeDateSus.text = list[position].fecha_cese.toString()
        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return  list.size
    }
}