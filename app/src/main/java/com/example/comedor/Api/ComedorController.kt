package com.example.comedor.Api

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class ComedorController(val context : Context) {

    private  var queue : RequestQueue = Volley.newRequestQueue(context)
    private val uri = "http://localhost/API-PHP/Api.php?apicall=readempleados"

//    fun obtenerDatos(){
//        var request : JsonObjectRequest = JsonObjectRequest(GET,uri,null,
//        Response.Listener {
//            //on response
//            //parsing del json
//            response ->
//            var myJsonArray = response.getJSONArray(
//
//            )
//
//        },
//        Response.ErrorListener {
//            // on error
//        })
//
//        queue.add(request)
//    }


}