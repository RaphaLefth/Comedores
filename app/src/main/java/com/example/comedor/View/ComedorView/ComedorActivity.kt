package com.example.comedor.View.ComedorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Adapters.ComedorAdapters
import com.example.comedor.Models.ItemBtnComedor
import com.example.comedor.Presenter.ComedorPresenter.ComedorPresenter
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ComedorActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : ComedorPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var arrayList: ArrayList<ItemBtnComedor>
    private lateinit var comedorAdapters: ComedorAdapters
    private lateinit var welcomeText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedor_screen)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = ComedorPresenter(mAuth,mDatabase,this)


        welcomeText = findViewById(R.id.txtWelcomeComedor)
        presenter.welcomeMsg(welcomeText)

        recyclerView = findViewById(R.id.my_recyclerV)
     //   linearLayoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        gridLayoutManager = GridLayoutManager(applicationContext,
        2,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataOnList()
        comedorAdapters = ComedorAdapters(applicationContext, arrayList)
        recyclerView.adapter =comedorAdapters

    }

    private fun setDataOnList() : ArrayList<ItemBtnComedor>{
        val itemBtnComedores : ArrayList<ItemBtnComedor> = ArrayList()
        itemBtnComedores.add(ItemBtnComedor(R.drawable.ic_personal_comedor_24,"Ver Personal"))
        itemBtnComedores.add(ItemBtnComedor(R.drawable.ic_see_comedor_24,"Ver Comedor"))
        itemBtnComedores.add(ItemBtnComedor(R.drawable.ic_report_info_24,"Reportes"))

        itemBtnComedores.add(ItemBtnComedor(R.drawable.ic_services_comedor_24,"Ver Servicios"))

        itemBtnComedores.add(ItemBtnComedor(R.drawable.ic_add_consumo_24,"Registrar Consumo"))
        return  itemBtnComedores
    }
}