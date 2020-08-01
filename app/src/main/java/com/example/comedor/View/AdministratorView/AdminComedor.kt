package com.example.comedor.View.AdministratorView

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Adapters.AdminComedorAdapter
import com.example.comedor.Adapters.AdminEmpleadosAdapter
import com.example.comedor.Models.Comedor
import com.example.comedor.Models.Employees
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.activity_admin_comedor.*
import org.json.JSONArray
import org.json.JSONObject


class AdminComedor : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    internal lateinit var adapter: AdminComedorAdapter
    private lateinit var btnagregar : Button
    private lateinit var btneditar : Button
    private  lateinit var nombre : EditText
    private  lateinit var adminName : EditText
    private  lateinit var telefono : EditText
    private  lateinit var correo : EditText

    internal var getUserDataList: ArrayList<Employees> = ArrayList()

    internal var comedoresList : ArrayList<Comedor> = ArrayList()
    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_comedor)
        supportActionBar?.hide()

        btnagregar = findViewById(R.id.btn_agregar_comedor)
        btneditar = findViewById(R.id.btn_editar_comedor)
        nombre = findViewById(R.id.edt_nombre)
        adminName = findViewById(R.id.edt_admin_name)
        telefono = findViewById(R.id.edt_telefono_comedor)
        correo = findViewById(R.id.edt_correo_comedor)
        queue= Volley.newRequestQueue(this)
        adapter = AdminComedorAdapter(this,comedoresList)
        swipeRefresh.setOnRefreshListener { setupRecyclerView() }
        setupRecyclerView()
        btnagregar.setOnClickListener{
            agregarComedor()

        }

//        adddata.setOnClickListener {
//            val i= Intent(this, AdminAddComedorActivity::class.java)
//            this.startActivity(i)
//        }
    }


    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        userrecyclerview.layoutManager = layoutManager
//        progressBar.visibility = View.VISIBLE
//        getEmpleados()
        getComedores()


    }

    private fun agregarComedor(){
        if(nombre.text.length==0){
            showToast("Falta el nombre")
        }else if(adminName.text.length==0){
            showToast("Falta el nombre del administrador")
        }else if(telefono.text.length==0){
            showToast("Falta el telefono")
        }else if(correo.text.length==0){
            showToast("Falta el correo")
        }
        else{
            saveData()
        }


    }

    private fun saveData(){

        val stringRequest = object : StringRequest(
            Method.POST,getString(R.string.baseurl)+"/comedor/nuevo", Response.Listener { response ->
                Log.e("nuevoComedor==>>",response.toString());
                try {
                    val jsonObject = JSONObject(response)
//                    showToast(jsonObject.getString("msg"))
                }
                catch (e: Exception) {
//                    Toast.makeText(this, "Exception error"+e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }) {
            //aca se parsea
            override fun getParams(): MutableMap<String,String> {
                val params = HashMap<String, String>()
                params["nombre"] = nombre.text.toString()
                params["administrador"] = adminName.text.toString()
                params["estado"] =  "1"
                params["telefono"] = telefono.text.toString()
                params["correo"] = correo.text.toString()

                return params


            }
        }
        queue.add(stringRequest)

    }
    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s, Toast.LENGTH_LONG).show()
    }







    private fun getComedores(){
        comedoresList.clear()
        val request = object : StringRequest(
            Method.GET,getString(R.string.baseurl)+"comedor",Response.Listener {response ->
            Log.e("comedor==>" ,response.toString());
                try {

                    val jsonObject = JSONObject(response)
                    val dataArray = jsonObject.getJSONArray("comedores")
                    for(i in 0 until dataArray.length()){
                        val obj = dataArray.getJSONObject(i)
                        val comedor = Comedor(
                            obj.getInt("id_comedor"),
                            obj.getString("nombre"),
                            obj.getString("administrador"),
                            obj.getInt("estado"),
                            obj.getString("telefono"),
                            obj.getString("correo"))
                        comedoresList.add(comedor)
                    }
                    adapter = AdminComedorAdapter(this,comedoresList)
                    userrecyclerview.adapter = adapter
                    swipeRefresh.isRefreshing = false
                    adapter.notifyDataSetChanged()
                }catch (e:java.lang.Exception){
//                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                    swipeRefresh.isRefreshing = false
                }
        },Response.ErrorListener {
                swipeRefresh.isRefreshing = false
//                Toast.makeText(this,
//                    "Something is wrong",
//                    Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
//                params.put("id_comedor","")
//                params.put("id_empresa","")
//                return params
                return  HashMap<String, String>()
            }

            override fun getHeaders(): MutableMap<String, String> {

                val headers = HashMap<String,String>()
                headers["id_comedor"] = ""
                headers["id_empresa"] = ""
                return  headers
            }
        }
        queue.add(request)
        adapter.notifyDataSetChanged()
    }



}