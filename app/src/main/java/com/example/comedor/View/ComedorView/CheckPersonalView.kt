package com.example.comedor.View.ComedorView

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Adapters.AdminEmpleadosAdapter
import com.example.comedor.Models.Employees
import com.example.comedor.Models.Empresa
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_check_personal_view.*
import org.json.JSONObject

class CheckPersonalView : AppCompatActivity() {

    private lateinit var spComedor : MaterialSpinner
    private lateinit var queue: RequestQueue
    internal var list : ArrayList<Empresa> = ArrayList()
    internal var listEmpleadosEmpresa: ArrayList<Employees> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_personal_view)
        supportActionBar!!.title = "Ver Personal"
        queue = Volley.newRequestQueue(this)
        spComedor = findViewById(R.id.sp_empresa_comedor)
        setSpinner()
        setupRecyclerView()
        btn_search.setOnClickListener {
            search()
        }
    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_personal_comedor.layoutManager = layoutManager
    }

    private fun setSpinner() {
        list.clear()
        val request = object : StringRequest(Method.GET,getString(R.string.baseurl)+"empresa",
                Response.Listener {
                    response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val dataArray = jsonObject.getJSONArray("empresas")
                        for(i in 0 until dataArray.length()){
                            val obj = dataArray.getJSONObject(i)
                            val empresa = Empresa(
                                obj.getString("ruc"),
                                obj.getString("nombre"),
                                obj.getString("sedes"))
                            list.add(empresa)
                        }
                        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spComedor.getSpinner().adapter = adapter
                    }catch (e : Exception){
                        e.printStackTrace()
                        list.clear()

                    }
                },Response.ErrorListener {
                list.clear()
            }) {

        }
        queue.add(request)
    }

    private fun search() {
        listEmpleadosEmpresa.clear()
        val empresa = spComedor.getSpinner().selectedItem as Empresa

        val request = object : StringRequest(Method.GET,getString(R.string.baseurl)+"empresa/empleados?ruc="+empresa.ruc
            ,Response.Listener {
                val jsonObject = JSONObject(it)
                val dataArray = jsonObject.getJSONArray("empresa_empleados")
                for(i in 0 until dataArray.length()){
                    val obj = dataArray.getJSONObject(i)
                    val empleados = Employees(
                        obj.getString("dni"),
                        obj.getString("nombre"),
                        obj.getString("apellido"),
                        obj.getInt("id_categoria"),
                        obj.getString("ruc")
                    ,obj.getInt("estado"))
                    listEmpleadosEmpresa.add(empleados)
                }

                val rvAdapter = AdminEmpleadosAdapter(this,listEmpleadosEmpresa)
                rv_personal_comedor.adapter = rvAdapter
                rvAdapter.notifyDataSetChanged()
            },Response.ErrorListener {
//                rvAdapter.notifyDataSetChanged()
            }){
            override fun getParams(): MutableMap<String, String> {
                return super.getParams()
            }
        }
        queue.add(request)
    }

}