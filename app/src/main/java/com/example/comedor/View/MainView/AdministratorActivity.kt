package com.example.comedor.View.MainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Presenter.ComedorPresenter.ComedorPresenter
import com.example.comedor.R
import com.example.comedor.Adapters.RecyclerAdapter
import com.example.comedor.Presenter.AdminPresenter.AdministratorPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
        rvtest = findViewById(R.id.rvtest)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = AdministratorPresenter(this,mDatabase,mAuth)
        rvtest.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        supportActionBar!!.title = "Administrador"
        var linearLayoutManager = LinearLayoutManager(this)

        rvtest.layoutManager = linearLayoutManager

        adapter = RecyclerAdapter(LISTA)
        rvtest.adapter = adapter
        txtWelcome = findViewById(R.id.txtWelcomeAdmin)
        presenter.welcomeMsg(txtWelcome)
    }

    companion object {
        private const val LISTA : Int = 100
    }
}