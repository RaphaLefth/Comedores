package com.example.comedor.View.ComedorView

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Models.Employees
import com.example.comedor.Models.Servicio
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_add_consumo_view.*
import org.json.JSONObject

class AddConsumoView : AppCompatActivity() {

    private lateinit var queue : RequestQueue

    private lateinit var spinner: MaterialSpinner

    internal var listServicio : ArrayList<Servicio> = ArrayList()
    internal var empleado : Employees ? = null
    internal var servicio : Servicio ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumo_view)
        supportActionBar!!.title = "Registrar Consumo"
        queue = Volley.newRequestQueue(this)
        spinner = findViewById(R.id.spServicios)
        setSpinner()
        btn_nuevo_servicio.setOnClickListener {
            registrarServicio()
        }


    }
    private fun obtenerEmpleado() : Employees? {

        val request = object : StringRequest(Method.POST,getString(R.string.baseurl)+"empleado/findbEmpleadbyDni"
        ,Response.Listener {
                val jsonObject = JSONObject(it)
                val data = jsonObject.getJSONArray("empleado")
                for (i in 0 until data.length()) {
//                        progressBar.visibility = View.GONE
                    val dataobj = data.getJSONObject(i)
                    val employe=
                        Employees(dataobj.getString("dni"),
                            dataobj.getString("nombre"),
                            dataobj.getString("apellido"),
                            dataobj.getInt("id_categoria"),
                            dataobj.getString("ruc_empresa"),
                            dataobj.getInt("estado"))
                    empleado = employe
                }
            },Response.ErrorListener {

            }){
            override fun getParams(): MutableMap<String, String> {
                return super.getParams()
            }
        }
        queue.add(request)
        return empleado
    }

    private fun registrarServicio() {
        empleado = obtenerEmpleado()
        servicio = spinner.getSpinner().selectedItem as Servicio?

    }

    private fun setSpinner() {
            listServicio.clear()
            val request = object : StringRequest(Method.GET,getString(R.string.baseurl)+"servicio/Empresa",
                Response.Listener {
                        response ->
                    try {
                        val jsonObject = JSONObject(response)
                        val dataArray = jsonObject.getJSONArray("servicios")
                        for(i in 0 until dataArray.length()){
                            val obj = dataArray.getJSONObject(i)
                            val servicio = Servicio(
                                obj.getString("id_servicio"),
                                obj.getString("descripcion"))
                            listServicio.add(servicio)
                        }
                        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,listServicio)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner.getSpinner().adapter = adapter
                    }catch (e : Exception){
                        e.printStackTrace()
                        listServicio.clear()

                    }
                }, Response.ErrorListener {
                    listServicio.clear()
                }) {

            }
            queue.add(request)
        }


}