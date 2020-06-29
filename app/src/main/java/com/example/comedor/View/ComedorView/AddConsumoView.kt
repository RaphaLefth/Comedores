package com.example.comedor.View.ComedorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.comedor.R
import com.example.comedor.Models.Servicio
import com.loopj.android.http.*
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import java.lang.Exception

class AddConsumoView : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var client: AsyncHttpClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_consumo_view)
        supportActionBar!!.title = "Registrar Consumo"
        spinner = findViewById(R.id.spServicios)
        client = AsyncHttpClient()

        setSpinner()



    }

    private fun setSpinner(){
        val url = ""
        client.post(url,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                if (statusCode == 200){
                    loadSpinner(responseBody.toString())
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

            }

        })
    }

    private fun loadSpinner(responseBody: String?) {
        var list = ArrayList<Servicio>()
        try {
            val json = JSONArray(responseBody)
            var i = 0
            while (i<json.length()){
                val service = Servicio(
                    json.getJSONObject(i).getString("id"),
                    json.getJSONObject(i).getString("id")
                )
                list.add(service)
                i++
            }
            val adapter = ArrayAdapter<Servicio>(this,android.R.layout.simple_spinner_item,
                list)
            spinner.adapter = adapter
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}