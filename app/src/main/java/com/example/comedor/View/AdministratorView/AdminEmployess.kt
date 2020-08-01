package com.example.comedor.View.AdministratorView


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Adapters.AdminEmpleadosAdapter
import com.example.comedor.AdminEmpleadoAgregar
import com.example.comedor.Models.Comedor
import com.example.comedor.Models.Employees
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_admin_employess.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AdminEmployess : AppCompatActivity(),  View.OnClickListener,AdapterView.OnItemSelectedListener {

    private lateinit var queue: RequestQueue
    internal lateinit var adapter: AdminEmpleadosAdapter
    private lateinit var btnagregar : Button
    private lateinit var btnbuscar : Button
    private  lateinit var dni : EditText
    private  lateinit var spinner: MaterialSpinner
    private lateinit var sp : Spinner

    internal var empleadosList : ArrayList<Employees> = ArrayList()
    internal var comedoresList : ArrayList<Comedor> = ArrayList()

    internal var displayedList : ArrayList<Employees> = ArrayList()
    override fun onResume() {
        super.onResume()
        setupRecyclerView()
        getComedores()
        getEmpleados()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_employess)

        supportActionBar!!.title = "Personal"
        sp = findViewById(R.id.sp_test)
//        btnbuscar = findViewById(R.id.btn_buscar_emp)
        btnagregar = findViewById(R.id.btn_agregar_emp)
//        dni = findViewById(R.id.edt_dni_emp)
        queue= Volley.newRequestQueue(this)
        setupRecyclerView()
        getComedores()
        Toast.makeText(applicationContext,"ad", Toast.LENGTH_SHORT).show()

        sp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view

//                Toast.makeText(applicationContext,""+comedoresList[position].nombre.toString(),Toast.LENGTH_LONG).show()
//                text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        swipeRefreshPersonal.setOnRefreshListener {
            setupRecyclerView()
        }
        btnagregar.setOnClickListener{
            agregarEmpleado()
        }


    }

    private fun agregarEmpleado() {
        val i  = Intent(applicationContext,AdminEmpleadoAgregar::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(i)
    }


    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_list_personal.layoutManager = layoutManager
        getEmpleados()
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

                    val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,comedoresList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    sp.adapter = adapter
//                    val adapter = AdminComedorAdapter(this,comedoresList)
//                    userrecyclerview.adapter = adapter
//                    swipeRefresh.isRefreshing = false
                }catch (e:java.lang.Exception){
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                    swipeRefreshPersonal.isRefreshing = false
                }
            },Response.ErrorListener {
                swipeRefreshPersonal.isRefreshing = false
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






    private fun getEmpleados(){
        empleadosList.clear()
        val stringRequest = object : StringRequest(
            Method.GET,
            getString(R.string.baseurl) + "empleado", Response.Listener { response ->
                Log.e("demo==>>",response.toString());
                try {
                    val jsonObject = JSONObject(response)
                    val dataArray = jsonObject.getJSONArray("empleados")
                    for (i in 0 until dataArray.length()) {
//                        progressBar.visibility = View.GONE
                        val dataobj = dataArray.getJSONObject(i)
                        val m=
                            Employees(dataobj.getString("dni"),
                                dataobj.getString("nombre"),
                                dataobj.getString("apellido"),
                                dataobj.getInt("id_categoria"),
                                dataobj.getString("ruc_empresa"),
                                dataobj.getInt("estado"))
                        empleadosList.add(m)
                    }
                    displayedList.addAll(empleadosList)
                    val adapter= AdminEmpleadosAdapter(this, displayedList)
                    rv_list_personal.adapter = adapter
                    swipeRefreshPersonal.isRefreshing = false


                } catch (e: Exception) {
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                    swipeRefreshPersonal.isRefreshing = false
                }
            }, Response.ErrorListener {

                swipeRefreshPersonal.isRefreshing = false
                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                return  HashMap<String, String>()

            }
        }
        queue.add(stringRequest)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_filter,menu)
        val item = menu!!.findItem(R.id.search_emp)

        if(item != null){
            val searchView = item.actionView as SearchView
            searchView.queryHint = "Buscar"
            searchView.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if(newText!!.isNotEmpty()){
                            displayedList.clear()
                            val search = newText.toLowerCase(Locale.getDefault())

                            empleadosList.forEach{
                                if(it.nombre!!.toLowerCase(Locale.getDefault()).contains(search)){
                                    displayedList.add(it)
                                }

                            }
                            rv_list_personal.adapter!!.notifyDataSetChanged()
                        }else{
                            displayedList.clear()
                            displayedList.addAll(empleadosList)
                            rv_list_personal.adapter!!.notifyDataSetChanged()
                        }
                        return true
                    }

                }
            )
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.search_emp){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            btnbuscar.id -> buscarEmpleado()
            btnagregar.id -> nuevoEmpleado()
        }
    }

    private fun nuevoEmpleado() {

    }

    private fun buscarEmpleado() {
//        val dni = dni.text.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}