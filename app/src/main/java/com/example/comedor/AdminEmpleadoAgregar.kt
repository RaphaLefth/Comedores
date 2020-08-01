package com.example.comedor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Models.Categoria
import com.example.comedor.Models.Employees
import kotlinx.android.synthetic.main.activity_admin_add_empleado.*
import org.json.JSONObject
import kotlin.collections.set

class AdminEmpleadoAgregar : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var  listEstado: Array<String>
    private lateinit var spCategoria : MaterialSpinner
    private lateinit var spEstado : MaterialSpinner
//    private lateinit var listCategoria : ArrayList<Categoria>
    private lateinit var queue: RequestQueue
    private lateinit var empleado : Employees
    internal var listCategoria : ArrayList<Categoria> = ArrayList()

    private var idCat : Int = 0
    private var idEstado : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_empleado)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        listEstado = resources.getStringArray(R.array.es)
        spCategoria = findViewById(R.id.sp_emp_categoria)
        spEstado = findViewById(R.id.sp_estado)

        queue = Volley.newRequestQueue(this)
        getCategoria()
        spCategoria.getSpinner().onItemSelectedListener = this
        spEstado.getSpinner().onItemSelectedListener = this

        if(spEstado !=null){
            val adapterEstado = ArrayAdapter(this,android.R.layout.simple_spinner_item,listEstado)
            adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spEstado.setAdapter(adapterEstado)
            spEstado.setLabel("Estado")
        }
    btn_agregar_emp_ruc.setOnClickListener {
        agregarEmpleado()
//        this.finish()
    }

    }

    private fun getCategoria(){
        listCategoria.clear()
        val request = object : StringRequest(
            Method.GET,getString(R.string.baseurl)+"categoria",Response.Listener {response ->
                Log.e("comedor==>" ,response.toString());
                try {

                    val jsonObject = JSONObject(response)
                    val dataArray = jsonObject.getJSONArray("categorias")
                    for(i in 0 until dataArray.length()){
                        val obj = dataArray.getJSONObject(i)
                        val categoria = Categoria(
                            obj.getInt("id"),
                            obj.getString("descripcion"))
                        listCategoria.add(categoria)
                    }

                    val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,listCategoria)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spCategoria.getSpinner().adapter = adapter
//                    val adapter = AdminComedorAdapter(this,comedoresList)
//                    userrecyclerview.adapter = adapter
//                    swipeRefresh.isRefreshing = false
                }catch (e:java.lang.Exception){
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            },Response.ErrorListener {

                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                return  HashMap<String, String>()
            }
        }
        queue.add(request)
    }


    private fun agregarEmpleado() {
     empleado = Employees(
        tx_emp_dni.text.toString().trim(),
        txt_emp_nombre.text.toString().trim(),
        tx_emp_apellido.text.toString().trim(),
        t(spCategoria.getSpinner().selectedItem.toString()),
        tx_emp_ruc_empresa.text.toString().trim(),
        tp(spEstado.getSpinner().selectedItem.toString()))

     val request = object : StringRequest(Method.POST,getString(R.string.baseurl)+"empleado/nuevo",
     Response.Listener {
         response->
         try {
             val json = JSONObject(response)
         Log.d("TAGF",json.getString("msg"))
         }catch (e : Exception){
             e.printStackTrace()
         }
     },Response.ErrorListener {  Toast.makeText(this,
             "Something went wrong",
             Toast.LENGTH_LONG).show() }){
         override fun getParams(): MutableMap<String, String> {
             val params = HashMap<String,String>()
             params["dni"] = empleado.dni.toString()
             params["nombre"] = empleado.nombre.toString()
             params["apellido"] = empleado.apellido.toString()
             params["id_categoria"] = empleado.id_categoria.toString()
             params["ruc_empresa"] = empleado.ruc_empresa.toString()
             params["estado"] = empleado.estado.toString()
             return params
         }
     }

        queue.add(request)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //user.typeUser = spinner.getSpinner().selectedItem.toString()
//        idCat = listCategoria[position].id
//        idEstado =   t(spEstado.getSpinner().selectedItem.toString())
//        empleado.id_categoria = listCategoria[position].id
//        Toast.makeText(this,""+empleado.id_categoria,Toast.LENGTH_LONG).show()
//        empleado.id_categoria = spCategoria.getSpinner().selectedItem.toString().toInt()
//        empleado.estado =  t(spEstado.getSpinner().selectedItem.toString())

    }
    private fun tp(s:String) : Int{
        return when(s){
            "Activo"->1
            "Inactivo"->2
            else->{
                0
            }
        }
    }
    private fun t(estado:String) : Int{
        return when(estado){
            "Empleado"->1
            "Obrero"->2
            else->{
                0
            }
        }
    }

}