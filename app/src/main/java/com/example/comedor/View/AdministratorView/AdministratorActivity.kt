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

    }

    private fun setDataOnList(): ArrayList<ItemBtnGeneric> {
        val items : ArrayList<ItemBtnGeneric> = ArrayList()
        items.add(ItemBtnGeneric(R.drawable.ic_admin_add_sucursal_24,"Comedor"))
        items.add(ItemBtnGeneric(R.drawable.ic_admin_personal_24,"Empleados"))
        items.add(ItemBtnGeneric(R.drawable.ic_admin_role_permission,"Gestionar Permisos"))//leer de fire y cambiar
        items.add(ItemBtnGeneric(R.drawable.ic_services_comedor_24,"Servicios"))
        return items
    }
}


