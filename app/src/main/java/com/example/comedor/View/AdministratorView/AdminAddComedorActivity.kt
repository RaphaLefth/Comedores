package com.example.comedor.View.AdministratorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.android.synthetic.main.activity_add_data.closeActivity
import kotlinx.android.synthetic.main.activity_add_data.submitdata
import org.json.JSONObject

class AdminAddComedorActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        queue= Volley.newRequestQueue(this)
        submitdata.setOnClickListener {
            senData()
            finish()
        }
        closeActivity.setOnClickListener {
        //    getback()
            finish()
        }

    }

    private fun getback() {

    }

    private fun senData(){
        if
                (name.text.length==0){
            showToast("Enter Your Name")
        }else if(email.text.length==0){
            showToast("Enter Your Email")
        }else if(address.text.length==0){
            showToast("Enter Your Address")
        }else{
            saveData()
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s, Toast.LENGTH_LONG).show()
    }




    private fun saveData(){

        val stringRequest = object : StringRequest(
            Method.POST,
            getString(R.string.baseurl)+"adduser.php", Response.Listener { response ->
                Log.e("demo==>>",response.toString());
                try {


                    val jsonObject = JSONObject(response)


                    showToast(jsonObject.getString("msg"))




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
                params["name"] = name.text.toString()
                params["email"] = email.text.toString()
                params["address"] = address.text.toString()

                return params

            }
        }
        queue.add(stringRequest)

    }

}
