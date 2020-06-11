package com.example.comedor.View.ComedorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.comedor.R

class ComedorInfoView : AppCompatActivity() {
    private lateinit var btnBack : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedor_info_view)
    }
}