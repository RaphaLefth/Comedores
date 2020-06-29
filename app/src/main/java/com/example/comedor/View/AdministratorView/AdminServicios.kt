package com.example.comedor.View.AdministratorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.R

class AdminServicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_excel)
        supportActionBar!!.title = "Servicios"
    }
}