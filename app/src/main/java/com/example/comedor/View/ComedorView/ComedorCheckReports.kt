package com.example.comedor.View.ComedorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.R

class ComedorCheckReports : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedor_check_reports)
        supportActionBar!!.title = "Ver Reportes"
    }
}