package com.example.comedor.View.MainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.Presenter.ComedorPresenter.ComedorPresenter
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ReporterActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : ComedorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = ComedorPresenter(mAuth,mDatabase,this)
        presenter.welcomeMsg()
    }
}
