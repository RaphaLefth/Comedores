package com.example.comedor.View.ComedorView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.comedor.R
import com.example.comedor.View.MainView.ComedorActivity
import kotlinx.android.synthetic.main.activity_services_comedor_info_view.*

class ServicesInfoView : AppCompatActivity() , View.OnClickListener{

    private lateinit var btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_comedor_info_view)
        btnBack = findViewById(R.id.btnReturn)
        btnBack.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Toast.makeText(this,"toque algo",Toast.LENGTH_LONG).show()
        when(p0!!.id){
            btnReturn.id -> getBackComedor()
        }
    }

    private fun getBackComedor() {
        finish()

    }
}