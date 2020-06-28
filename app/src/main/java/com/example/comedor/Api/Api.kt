package com.example.comedor.Api

import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ListAdapter
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Request.Method.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.comedor.Adapters.EmployeeAdapter
import com.example.comedor.Models.Employees
import com.google.gson.annotations.JsonAdapter
import kotlinx.android.synthetic.main.activity_admin_screen.*
import kotlinx.android.synthetic.main.activity_admin_screen.view.*
import org.json.JSONArray
import org.json.JSONException
import java.lang.reflect.Array
import java.net.HttpURLConnection
import java.net.URL

class Api(var context: Context, val view : View) {
//    private val URL_BASE: String = "http://localhost/API-PHP/Api.php"
//    private val ROOT_URL :String =URL_BASE + "?apicall="
//    private val  URL_CREATE_USUARIO : String = ROOT_URL + "createusuario";
//    private val  URL_READ_USUARIOS :String = ROOT_URL + "readusuarios";
  private  val uri="http://192.168.1.37/API-PHP/Api.php?apicall=readempleados"


  fun obteneData(){
    var request = JsonObjectRequest(GET,uri,null, Response.Listener {
            response ->
      try {
          var mJsonArray= response.getJSONArray("contenido")
          val list = ArrayList<Employees>()
          var x = 0
          while(x<mJsonArray.length()){
            val jsonObject = mJsonArray.getJSONObject(x)
              list.add(
                Employees(
                  jsonObject.getInt("id_empleado"),
                  jsonObject.getString("dni"),
                  jsonObject.getString("nombre"),
                  jsonObject.getString("apellido"),
                  jsonObject.getString("categoria"),
                  jsonObject.getInt("id_empresa"),
                  jsonObject.getInt("estado")
                )
              )
              x++
            val adapter = EmployeeAdapter(context,list)
            view.rvEmpleados.adapter = adapter
        }
      }catch ( e : JSONException){

      }
    },
    Response.ErrorListener {
      error ->
      //un error :v
    })
  }



//
//    fun execute(){}
//    override fun doInBackground(vararg url: String?): String {
//        var text : String
//        val connection =URL(url[0]).openConnection() as HttpURLConnection
//        try {
//            connection.connect()
//            text = connection.inputStream.use { it.reader().use{
//                reader -> reader.readText()
//            } }
//        }finally {
//            connection.disconnect()
//        }
//        return text
//    }
//
//    override fun onPostExecute(result: String?) {
//        super.onPostExecute(result)
//        handleJson(result)
//    }
//
//    private fun handleJson(jsonString: String?) {
//        val jsonArray = JSONArray(jsonString)
//        val list = ArrayList<Employees>()
//        var x = 0
//        while (x< jsonArray.length()){
//            val jsonObject = jsonArray.getJSONObject(x)
//            list.add(
//                Employees(
//                jsonObject.getInt("id_empleado"),
//                    jsonObject.getString("dni"),
//                    jsonObject.getString("nombre"),
//                    jsonObject.getString("apellido"),
//                    jsonObject.getString("categoria"),
//                    jsonObject.getInt("id_empresa"),
//                    jsonObject.getInt("estado")
//                //    jsonObject.getString("fecha_ingreso"),
//                  //  jsonObject.getString("fecha_cese")
//            )
//            )
//            x++
//        }
//        val adapter = EmployeeAdapter(this,list)
//    }
}