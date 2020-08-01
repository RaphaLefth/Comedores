package com.example.comedor.View.AdministratorView

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.comedor.Models.Employees
import com.example.comedor.Models.User
import com.example.comedor.R
import kotlinx.android.synthetic.main.activity_admin_empleado_info.*

class Admin_empleado_info : AppCompatActivity() {
    private lateinit var empleado : Employees
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_empleado_info)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if(savedInstanceState !=null){
            empleado = savedInstanceState.getSerializable("usersaved") as Employees
        }else{
            try {
                empleado = (intent.getSerializableExtra("getEmpleadoInf") as Employees?)!!

            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        setInfo(empleado)
    }



    private fun setInfo(empleado: Employees) {
        emp_nm.text = empleado.nombre
        emp_ln.text = empleado.apellido
        emp_dni.text = empleado.dni
        emp_estado.text = validar()
        emp_cat.text = setCat()
        emp_ruc.text = empleado.ruc_empresa
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Toast.makeText(this,":V",Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
        this.finish()
    }

    private fun setCat(): String {
        return when(empleado.id_categoria){
             1 -> "Empleado"
            2-> "Obrero"
            else -> {
                "Sin categoria"
            }
        }
    }

    private fun validar(): String {
        return when(empleado.estado){
            1 -> "Activo"
            2->"Inactivo"
            3-> "Baja"
            else->{
                "Sin estado"
            }
        }
    }
}