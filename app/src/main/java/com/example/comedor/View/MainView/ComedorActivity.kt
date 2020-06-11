package com.example.comedor.View.MainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Adapters.AlphaAdapters
import com.example.comedor.Models.CharItem
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
    private lateinit var gridLayoutManager: LinearLayoutManager
    private lateinit var arrayList: ArrayList<CharItem>
    private lateinit var alphaAdapters: AlphaAdapters
    private lateinit var grid : GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedor_screen)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = ComedorPresenter(mAuth,mDatabase,this)
        presenter.welcomeMsg()

        recyclerView = findViewById(R.id.my_recyclerV)
        gridLayoutManager = GridLayoutManager(applicationContext,
        3,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataOnList()
        alphaAdapters = AlphaAdapters(applicationContext,arrayList!!)
        recyclerView?.adapter =alphaAdapters

    }

    private fun setDataOnList() : ArrayList<CharItem>{
        var items : ArrayList<CharItem> = ArrayList()
        items.add(CharItem(R.drawable.ic_personal_comedor_24,"Ver Personal"))
        items.add(CharItem(R.drawable.ic_see_comedor_24,"Ver Comedor"))
        items.add(CharItem(R.drawable.ic_report_info_24,"Reportes"))

        items.add(CharItem(R.drawable.ic_services_comedor_24,"Ver Servicios"))

        items.add(CharItem(R.drawable.ic_add_consumo_24,"Registrar Consumo"))
        return  items
    }
//
//    override fun onClick(p0: View?) {
//        when(p0!!.id){
//            R.layout.grid_layout_list_item -> Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
//        }
//    }

//    private fun setToggleEvent(grid : GridLayout){
//        var g = grid.childCount as Int
//        for(){
//
//        }
//        for{
//            var card : CardView = grid.getChildAt(i) as CardView
//            card.setOnClickListener {
//                Toast.makeText(this@ComedorActivity,"clicked",
//                    Toast.LENGTH_LONG).show()
//            }
//        }
//    }

}