package com.example.comedor.View.AdministratorView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Adapters.AdminComedorAdapter
import com.example.comedor.Adapters.AdminEmpleadosAdapter
import com.example.comedor.Api.Api
import com.example.comedor.Models.ComedorModelData
import com.example.comedor.Models.Employees
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_admin_comedor.*
import org.json.JSONObject

class AdminComedor : AppCompatActivity() {

    internal lateinit var queue: RequestQueue
    internal lateinit var adapter: AdminComedorAdapter

    internal var getUserDataList: ArrayList<Employees> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_comedor)
        supportActionBar?.hide()
        queue= Volley.newRequestQueue(this)

        swipeRefresh.setOnRefreshListener { setupRecyclerView() }
        setupRecyclerView()
        adddata.setOnClickListener {
            val i= Intent(this, AdminAddComedorActivity::class.java)
            this.startActivity(i)
        }
    }


    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        userrecyclerview.layoutManager = layoutManager
        progressBar.visibility = View.VISIBLE
        getEmpleados()


    }


    private fun getEmpleados(){
        getUserDataList.clear()
        val stringRequest = object : StringRequest(
            Method.POST,
            getString(R.string.baseurl1), Response.Listener { response ->
                Log.e("demo==>>",response.toString());
                try {
                    val jsonObject = JSONObject(response)
                    val dataArray = jsonObject.getJSONArray("empleados")
                    for (i in 0 until dataArray.length()) {
                        progressBar.visibility = View.GONE
                        val dataobj = dataArray.getJSONObject(i)
                        val m=
                            Employees(dataobj.getString("dni"),
                            dataobj.getString("nombre"),
                            dataobj.getString("apellido"),
                            dataobj.getInt("id_categoria"),
                            dataobj.getString("ruc_empresa"),
                            dataobj.getInt("estado"))
                        getUserDataList.add(m)
                    }
                    val adapter= AdminEmpleadosAdapter(this, getUserDataList)
                    userrecyclerview.adapter = adapter
                    swipeRefresh.isRefreshing = false


                } catch (e: Exception) {
                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                    swipeRefresh.isRefreshing = false
                }
            }, Response.ErrorListener {

                swipeRefresh.isRefreshing = false
                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                return  HashMap<String, String>()
                /*               params["page"] = "1"
                               params["exam_type"] ="m"*/

//                return params

            }
        }
        queue.add(stringRequest)

    }
/**
//    private fun getData(){
//        getUserDataList.clear()
//        val stringRequest = object : StringRequest(
//            Method.POST,
//            getString(R.string.baseurl)+"getdata.php", Response.Listener { response ->
//                Log.e("demo==>>",response.toString());
//                try {
//                    val jsonObject = JSONObject(response)
//
//                    val dataArray = jsonObject.getJSONArray("data")
//                    for (i in 0 until dataArray.length()) {
//                        progressBar.visibility = View.GONE
//                        val dataobj = dataArray.getJSONObject(i)
//                        val m=ComedorModelData(dataobj.getString("id"),
//                        dataobj.getString("Name"),
//                        dataobj.getString("email"),
//                        dataobj.getString("address"))
//                        getUserDataList.add(m)
//                    }
//                    val adapter= AdminComedorAdapter(this, getUserDataList)
//                    userrecyclerview.adapter = adapter
//                    swipeRefresh.isRefreshing = false
//
//
//                } catch (e: Exception) {
//                    Toast.makeText(this, "Exception error", Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//
//                    swipeRefresh.isRefreshing = false
//                }
//            }, Response.ErrorListener {
//
//                swipeRefresh.isRefreshing = false
//                Toast.makeText(this,
//                    "Something is wrong",
//                    Toast.LENGTH_LONG).show()
//            }) {
//            override fun getParams(): MutableMap<String, String> {
//                return  HashMap<String, String>()
//                /*               params["page"] = "1"
//                               params["exam_type"] ="m"*/
//
////                return params
//
//            }
//        }
//        queue.add(stringRequest)
//
//    }**/

}