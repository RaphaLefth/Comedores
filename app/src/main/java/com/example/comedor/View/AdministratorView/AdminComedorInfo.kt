package com.example.comedor.View.AdministratorView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comedor.Models.Comedor
import com.example.comedor.Models.User
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_admin_comedor_info.*
import org.json.JSONObject

class AdminComedorInfo : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_comedor_info)
        val comedor = intent.getSerializableExtra("getComedorInf") as Comedor?
        Toast.makeText(this,comedor.toString(), Toast.LENGTH_LONG).show()
        queue= Volley.newRequestQueue(this)
        setDatos(comedor)
        btn_activar_editar.setOnClickListener{
            et_comedor_nombre.isEnabled = true
            et_comedor_administrador.isEnabled = true
            et_comedor_estado.isEnabled = true
            et_comedor_telefono.isEnabled = true
            et_comedor_correo.isEnabled = true
            btn_regresar.isEnabled = true
        }

        btn_regresar.setOnClickListener {
            actualizarComedor(comedor)
            finish()
        }
    }

    private fun setDatos(comedor: Comedor?) {
        et_comedor_nombre.setText(comedor!!.nombre)
        et_comedor_administrador.setText(comedor.administrador)
        et_comedor_estado.setText(comedor.estado.toString())
        et_comedor_telefono.setText(comedor.telefono)
        et_comedor_correo.setText(comedor.email)
    }

    private fun actualizarComedor(comedor: Comedor?) {

        comedor!!.nombre = et_comedor_nombre.text.toString()
        comedor.administrador = et_comedor_administrador.text.toString()
        comedor.estado = et_comedor_estado.text.toString().toInt()
        comedor.telefono = et_comedor_telefono.text.toString()
        comedor.email = et_comedor_correo.text.toString()

        actualizar(comedor)

    }


    private fun showToast(s: String) {
        Toast.makeText(applicationContext,s, Toast.LENGTH_LONG).show()
    }


    private fun actualizar(comedor: Comedor) {
        val stringRequest = object : StringRequest(Method.POST,getString(R.string.baseurl)+"comedor/actualizar",
                            Response.Listener {
                                response ->
                                Log.e("comedor/actualizar==>",response.toString())
                                try {
                                    val jsonObject = JSONObject(response)

//                                    showToast(jsonObject.getString("msg"))
                                }catch (e: Exception) {
                                    Toast.makeText(this, "Exception error"+e.message, Toast.LENGTH_SHORT).show()
                                    e.printStackTrace()
                                }
                            },Response.ErrorListener {
                                Toast.makeText(this,"Something is wrong",Toast.LENGTH_LONG).show()
            }) {

            //parseo

            override fun getParams(): MutableMap<String,String> {
                val params = HashMap<String, String>()
                params["id_comedor"] = comedor.id_comedor.toString()
                params["nombre"] = comedor.nombre
                params["administrador"] = comedor.administrador.toString()
                params["estado"] = comedor.estado.toString()
                params["telefono"] = comedor.telefono.toString()
                params["correo"] = comedor.email.toString()

                return params

            }

        }
        queue.add(stringRequest)
    }
}