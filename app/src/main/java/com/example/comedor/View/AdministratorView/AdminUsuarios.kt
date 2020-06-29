package com.example.comedor.View.AdministratorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.R

class AdminUsuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_services)

        supportActionBar!!.title = "Usuarios"
    }
}