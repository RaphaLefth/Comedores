package com.example.comedor.Api

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import com.example.comedor.Adapters.EmployeeAdapter
import com.example.comedor.Models.Employees
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_admin_screen.view.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class AsyncTaskHandleJson(val context: Context, var view : View)
    /**AsyncTask<String, String, String>()**/{

//
//    override fun doInBackground(vararg url: String?): String {
//        var text : String
//        val connection = URL(url[0]).openConnection() as HttpURLConnection
//        try {
//            connection.connect()
//            text = connection.inputStream.use { it.reader().use{
//                    reader -> reader.readText()
//            } }
//        }finally {
//            connection.disconnect()
//        }
//        return text
//    }
//    override fun onPostExecute(result: String?) {
//        super.onPostExecute(result)
//        handleJson(result)
//    }
//
//    private fun handleJson(jsonString: String?) {
//        //val jsonObject  = JsonObject(jsonString)
//        val jsonArray = JSONArray(jsonString)
//
//            val list = ArrayList<Employees>()
//        var x = 0
//        while (x< jsonArray.length()){
//            Log.i("tagconvertstr", "[$x]");
//            val jsonObject = jsonArray.getJSONObject(x)
//            list.add(
//                Employees(
//                    jsonObject.getInt("id_empleado"),
//                    jsonObject.getString("dni"),
//                    jsonObject.getString("nombre"),
//                    jsonObject.getString("apellido"),
//                    jsonObject.getString("categoria"),
//                    jsonObject.getInt("id_empresa"),
//                    jsonObject.getInt("estado")
//                )
//            )
//            x++
//        }
//        val adapter = EmployeeAdapter(context,list)
//       // view.rvEmpleados.adapter = adapter
////        employees_list.adapter = adapter
//    }

}