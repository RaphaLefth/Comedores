package com.example.comedor.View.AdministratorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Adapters.AdminServicioAdapter
import com.example.comedor.Models.Comedor
import com.example.comedor.Models.Empresa
import com.example.comedor.Models.ServiceEmpresa
import com.example.comedor.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_admin_services_main.*
import org.json.JSONObject
import java.lang.Exception

class AdminServicios : AppCompatActivity() {

    private lateinit var queue : RequestQueue
    private lateinit var spEmpresa : Spinner
    private lateinit var spComedor : Spinner
    private lateinit var btnSearch : Button
    private lateinit var rv : RecyclerView

    internal var comedorServicioList : ArrayList<ServiceEmpresa> =ArrayList()
    internal var comedorList : ArrayList<Comedor> =ArrayList()
    internal var empresasList : ArrayList<Empresa> =ArrayList()

    internal var idComedor: String? = null
    internal var idEmpresa : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_services_main)
        supportActionBar!!.title = "Servicios"

        spComedor = findViewById(R.id.sp_comedores)
        spEmpresa = findViewById(R.id.sp_empresas)

        queue = Volley.newRequestQueue(this)

        setupRecyclerView()
        setSpinners()


        spComedor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long){
//                Toast.makeText()
            idComedor=comedorList[position].id_comedor.toString()

            }

        }
        spEmpresa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                idEmpresa=empresasList[position].ruc
                setComedorServicioData(idComedor, idEmpresa)
            }


        }
        sr_comedor_servicio.setOnRefreshListener {
            setComedorServicioData(idComedor,idEmpresa)
        }
//        setComedorServicioData(idComedor, idEmpresa)
    }

    private fun setComedorServicioData(idComedor: String?, idEmpresa: String?) {
        comedorServicioList.clear()


        try {
            if(idComedor!=null && idEmpresa!=null){
                val request = object : StringRequest(
                    Method.GET,getString(R.string.baseurl)+"administrador/verServiciosComedor?id_comedor="+idComedor+"&id_empresa="+idEmpresa,Response.Listener {
                            response->
                        try {
                            val json = JSONObject(response)
//                            json.get
                            val dataArray = json.getJSONArray("servicios")
                            for (i in 0 until dataArray.length()){
                                val obj = dataArray.getJSONObject(i)
                                val servicio  = ServiceEmpresa(
                                    obj.getString("id_comedor_servicio"),
                                    obj.getString("id_empresa"),
                                    obj.getString("id_comedor"),
                                    obj.getString("id_servicio"),
                                    obj.getString("nombre"),
                                    obj.getString("estado"),
                                    obj.getString("descripcion"),
                                    obj.getString("precio"))
                                comedorServicioList.add(servicio)
                            }

                            val adapter = AdminServicioAdapter(this,comedorServicioList)
                            rv_servicios_disponibles.adapter = adapter
                            sr_comedor_servicio.isRefreshing = false

                        }catch (e : Exception){
                            Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                            e.printStackTrace()

                            sr_comedor_servicio.isRefreshing = false
                        }
                    }, Response.ErrorListener {
                        sr_comedor_servicio.isRefreshing = false
                        Toast.makeText(this,
                            "Something is wrong",
                            Toast.LENGTH_LONG).show()
                    }
                ){
                    override fun getParams(): MutableMap<String, String> {
                        return  HashMap<String, String>()

                    }

//                    override fun getHeaders(): MutableMap<String, String> {
//                        val headers = HashMap<String,String>()
//                        headers["Content-Type"]="application/json"
//                        headers["id_comedor"]=idComedor.toString()
//                        headers["id_empresa"]=idEmpresa.toString()
//                        return headers
//                    }
                }
                queue.add(request)
            }else{
               Snackbar.make(root_layout,"Seleccione una empresa o comedor ",Snackbar.LENGTH_LONG)
                   .show()
            }

        }catch (e : Exception){
            e.printStackTrace()
        }




    }

    private fun setSpinners() {
        comedorList.clear()
        empresasList.clear()
        val requestComedor = object : StringRequest(
            Method.GET,getString(R.string.baseurl)+"comedor", Response.Listener { response ->
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
                        comedorList.add(comedor)
                    }

                    val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,comedorList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spComedor.adapter = adapter
                }catch (e:java.lang.Exception){
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                    sr_comedor_servicio.isRefreshing = false
                }
            }, Response.ErrorListener {
                sr_comedor_servicio.isRefreshing = false
                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                return  HashMap<String, String>()
            }
        }


        val requestEmpresa = object : StringRequest(
            Method.GET,getString(R.string.baseurl)+"empresa",Response.Listener {
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
                        empresasList.add(empresa)
                    }

                    val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,empresasList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spEmpresa.adapter = adapter
                }catch (e:java.lang.Exception){
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                    sr_comedor_servicio.isRefreshing = false
                }
            },Response.ErrorListener {

            }
        ){
            override fun getParams(): MutableMap<String, String> {
                return  HashMap<String, String>()
            }
        }
        queue.add(requestComedor)
        queue.add(requestEmpresa)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_servicios_disponibles.layoutManager = layoutManager
    }
}