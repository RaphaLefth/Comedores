package com.example.comedor.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Models.Employees
import com.example.comedor.R
import com.example.comedor.View.AdministratorView.AdminEditDataComedorActivity
import com.example.comedor.View.AdministratorView.AdminEditUserData
import com.example.comedor.View.AdministratorView.Admin_empleado_info
import kotlinx.android.synthetic.main.admin_employe_layout_list.view.*
//import kotlinx.android.synthetic.main.admincomedorlayout.view.delete_data
//import kotlinx.android.synthetic.main.admincomedorlayout.view.edite_data
import org.json.JSONObject
import java.io.File

class AdminEmpleadosAdapter(val context: Context, private val empleados: ArrayList<Employees>)
    : RecyclerView.Adapter<AdminEmpleadosAdapter.MyEmployeeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEmployeeViewHolder {

        val view = LayoutInflater.from(context).inflate(
            R.layout.admin_employe_layout_list
            , parent, false)
        return MyEmployeeViewHolder(view)

    }

    override fun getItemCount(): Int {
        return  empleados.size

    }

    override fun onBindViewHolder(holder: MyEmployeeViewHolder, position: Int) {

        val userlistdataData=empleados[position]
        holder.setData(userlistdataData,position)

    }



    inner class MyEmployeeViewHolder(itemsEmpleadoView: View) : RecyclerView.ViewHolder(itemsEmpleadoView){
        private var currentEmployeelist: Employees? = null
        private var currentPosition: Int = 0


//
//        init {
//            //delete button
////            itemsEmpleadoView.delete_data.setOnClickListener {
////
////                deleteData(currentEmployeelist!!.dni)
////
////                empleados.removeAt(currentPosition)
////
////                notifyDataSetChanged()
////            }

        init {
            //click sobre el recycler
            itemView.setOnClickListener {
                val i = Intent(context,Admin_empleado_info::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val userPosition = empleados[currentPosition]

                i.putExtra("getEmpleadoInf", userPosition)
                context.startActivity(i)
            }
        }
//            //edit button
////            itemsEmpleadoView.edite_data.setOnClickListener {
////
////                val i= Intent(context, AdminEditDataComedorActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
////                val employerPosition = empleados[currentPosition]
////                i.putExtra("getEmployed",employerPosition)
////                context.startActivity(i)
////            }
//
//        }
        fun setData(employeerslistData: Employees?, pos: Int) {


            employeerslistData?.let {
                itemView.emp_dni.text = employeerslistData.dni
                itemView.emp_name.text = employeerslistData.nombre
                itemView.emp_lastname.text = employeerslistData.apellido
//                itemView.emp_cat.text = employeerslistData.id_categoria.toString()
//                itemView.ruc_empresa_emp.text = employeerslistData.ruc_empresa
//                itemView.estado_emp.text = employeerslistData.estado.toString()


            }
            this.currentEmployeelist = employeerslistData
            this.currentPosition = pos
        }
    }



    fun deleteData(uid:String?){
        val stringRequest = object : StringRequest(
            Method.POST,
            "http://bhssolution.com/data/api/kotlinapi/delete.php", Response.Listener { response ->
                Log.e("demo==>>",response.toString());
                try {


                    val jsonObject = JSONObject(response)


                    Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show()




                } catch (e: Exception) {
                    Toast.makeText(context, "Exception error"+e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()


                }
            }, Response.ErrorListener {


                Toast.makeText(context,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                return params

            }
            /* @Throws(AuthFailureError::class)
             override fun getHeaders(): Map<String, String> {
                 val params = HashMap<String, String>()


                 return params
             }*/
        }

        var queue: RequestQueue = Volley.newRequestQueue(context)

        queue.add(stringRequest)
    }


}