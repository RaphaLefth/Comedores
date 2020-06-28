package com.example.comedor.View.ComedorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.R

class AddConsumoView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumo_view)
        supportActionBar!!.title = "Registrar Consumo"
    }
}