package com.example.comedor.View.MainView

import android.os.AsyncTask
import android.os.AsyncTask.execute
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Adapters.EmployeeAdapter
import com.example.comedor.Presenter.ComedorPresenter.ComedorPresenter
import com.example.comedor.R
import com.example.comedor.Adapters.RecyclerAdapter
import com.example.comedor.Api.Api
import com.example.comedor.Models.Employees
import com.example.comedor.Presenter.AdminPresenter.AdministratorPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_admin_screen.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.text.DateFormat
import java.text.SimpleDateFormat

class AdministratorActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : AdministratorPresenter
    private lateinit var rvtest : RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var txtWelcome : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_screen)
       // rvtest = findViewById(R.id.rvtest)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = AdministratorPresenter(this,mDatabase,mAuth)
//        rvtest.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        supportActionBar!!.title = "Administrador"
        val url="http://192.168.1.38/API-PHP/Api.php?apicall=readempleados"

        AsyncTaskHandleJson().execute(url)
        var linearLayoutManager = LinearLayoutManager(this)

//        rvtest.layoutManager = linearLayoutManager
//
//        adapter = RecyclerAdapter(LISTA)
//        rvtest.adapter = adapter
        txtWelcome = findViewById(R.id.txtWelcomeAdmin)
        presenter.welcomeMsg(txtWelcome)
    }

    companion object {
        private const val LISTA : Int = 100
    }

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
                var df : DateFormat
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
            employees_list.adapter = adapter
        }

    }
}


