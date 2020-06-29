package com.example.comedor.View.AdministratorView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Models.ComedorModelData
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_edite_data.*
import org.json.JSONObject


class AdminEditDataComedorActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    private lateinit var g: Intent;
    internal lateinit var  userdata: ComedorModelData
    internal var getUserDataList: ArrayList<ComedorModelData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edite_data)
        g = intent
        queue= Volley.newRequestQueue(this)
        submitdata.setOnClickListener {
            senData()
        }
//        Log.e("currentlist",g.getStringExtra("getlistData"))
        closeActivity.setOnClickListener {
            finish()
        }

        userdata = g.getSerializableExtra("getlistData") as ComedorModelData

        Log.e("currentlist->",userdata.toString())
        ename.setText(userdata.nombreComedor)
        eemail.setText(userdata.owner)
        eaddress.setText(userdata.ruc)
    }

    private fun senData(){
        when {
            ename.text.isEmpty() -> {
                showToast("Enter Your Name")
            }
            eemail.text.isEmpty() -> {
                showToast("Enter Your Email")
            }
            eaddress.text.isEmpty() -> {
                showToast("Enter Your Address")
            }
            else -> {
                saveData()
            }
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }


    private fun saveData(){

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            getString(R.string.baseurl)+"updateuser.php", Response.Listener { response ->
                Log.e("demo==>>",response.toString());
                try {


                    val jsonObject = JSONObject(response)




                    showToast(jsonObject.getString("msg"))


                    val i= Intent(this,AdminComedor::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)

                } catch (e: Exception) {
                    Toast.makeText(this, "Exception error"+e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()


                }
            }, Response.ErrorListener {


                Toast.makeText(this,
                    "Something is wrong",
                    Toast.LENGTH_LONG).show()
            }) {


            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = userdata.idComedor
                params["name"] = ename.text.toString()
                params["email"] = eemail.text.toString()
                params["address"] = eaddress.text.toString()

                return params

            }
            /* @Throws(AuthFailureError::class)
             override fun getHeaders(): Map<String, String> {
                 val params = HashMap<String, String>()


                 return params
             }*/
        }
        queue.add(stringRequest)

    }
}