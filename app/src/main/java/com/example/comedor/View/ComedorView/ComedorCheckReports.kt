package com.example.comedor.View.ComedorView

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import com.example.comedor.R
import java.util.*

class ComedorCheckReports : AppCompatActivity() {

    private lateinit var buttonDate : Button
    private lateinit var btnSearch : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comedor_check_reports)
        supportActionBar!!.title = "Ver Reportes"
        buttonDate = findViewById(R.id.btnFecha)
        btnSearch = findViewById(R.id.buscar)
        buttonDate.setOnClickListener(){

            val now = Calendar.getInstance()

            val dataPicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
                Toast.makeText(this,"$year + ${month}+ $dayOfMonth",Toast.LENGTH_LONG).show()
            },now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            val month = now.get(Calendar.MONTH)
            dataPicker.show()
            btnSearch.setOnClickListener{
                Toast.makeText(this,
                   ""+ month,Toast.LENGTH_LONG).show()
            }

        }
    }
}