package com.example.comedor.View.AdministratorView

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Adapters.AdminAdapter
import com.example.comedor.Adapters.EmployeeAdapter
import com.example.comedor.R
import com.example.comedor.Adapters.RecyclerAdapter
import com.example.comedor.Models.Employees
import com.example.comedor.Models.ItemBtnGeneric
import com.example.comedor.Presenter.AdminPresenter.AdministratorPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class AdministratorActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : AdministratorPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var arrayList: ArrayList<ItemBtnGeneric>
    private lateinit var adminAdapter: AdminAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_screen)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = AdministratorPresenter(this,mDatabase,mAuth)
        supportActionBar!!.title = "Administrador"


        recyclerView = findViewById(R.id.rvMain)
        gridLayoutManager = GridLayoutManager(applicationContext,2,
            LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataOnList()
        adminAdapter = AdminAdapter(applicationContext,arrayList)
        recyclerView.adapter = adminAdapter


/**        rvtest = findViewById(R.id.rvEmpleados)
//        gridLayoutManager = GridLayoutManager(applicationContext,1,LinearLayoutManager.VERTICAL,false)
//        rvtest.layoutManager = gridLayoutManager
//        rvtest.setHasFixedSize(false)

        //api call
     //   val url="http://192.168.1.37/API-PHP/Api.php?apicall=readempleados"
    //    AsyncTaskHandleJson().execute(url)
        //end call
//        txtWelcome = findViewById(R.id.txtWelcomeAdmin)
       presenter.welcomeMsg(txtWelcome)**/
    }

    private fun setDataOnList(): ArrayList<ItemBtnGeneric> {
        val items : ArrayList<ItemBtnGeneric> = ArrayList()
        items.add(ItemBtnGeneric(R.drawable.ic_admin_add_sucursal_24,"Comedor"))
        items.add(ItemBtnGeneric(R.drawable.ic_admin_personal_24,"Empleados"))
        items.add(ItemBtnGeneric(R.drawable.ic_admin_role_permission,"Gestionar Permisos"))//leer de fire y cambiar
        items.add(ItemBtnGeneric(R.drawable.ic_services_comedor_24,"Servicios"))
        return items
    }
/*
    inner class AsyncTaskHandleJson: AsyncTask<String,String,String>(){

        override fun doInBackground(vararg url: String?): String {
            var text : String
            val connection =URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use{
                        reader -> reader.readText()
                } }
            }finally {
                connection.disconnect()
            }
            return text
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }

        private fun handleJson(jsonString: String?) {
            val jsonArray = JSONArray(jsonString)
            val list = ArrayList<Employees>()
            var x = 0
            while (x< jsonArray.length()){
                Log.i("tagconvertstr", "[$x]");
                val jsonObject = jsonArray.getJSONObject(x)
                list.add(
                    Employees(
                        jsonObject.getInt("id_empleado"),
                        jsonObject.getString("dni"),
                        jsonObject.getString("nombre"),
                        jsonObject.getString("apellido"),
                        jsonObject.getString("categoria"),
                        jsonObject.getInt("id_empresa"),
                        jsonObject.getInt("estado")
                    )
                )
                x++
            }
            val adapter = EmployeeAdapter(this@AdministratorActivity,list)
            rvtest.adapter = adapter
        }

    }*/
}


