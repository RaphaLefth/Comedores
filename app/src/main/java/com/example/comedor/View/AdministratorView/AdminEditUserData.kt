package com.example.comedor.View.AdministratorView

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AlertDialogLayout
import com.example.comedor.Models.User
import com.example.comedor.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_admin_edit_user_data.*

class AdminEditUserData : AppCompatActivity() {
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_user_data)
        val user = intent.getSerializableExtra("getUserInf") as User?
        Toast.makeText(this,user.toString(),Toast.LENGTH_LONG).show()
        setUserInfo(user)

        editPermisos.setOnClickListener {
//            val db = AlertDialog.Builder(this,R.style.Widget_AppCompat_ButtonBar_AlertDialog)
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)
            val v : View  = layoutInflater.inflate(R.layout.change_type_dialog_spinner,null)
            dialog.setTitle("Elija")
//            var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,
//            android.R.layout.simple_spinner_dropdown_item,resources.)

        }


        btnEnableEditUser.setOnClickListener {
            updateUser(user)
        }
    }

    private fun updateUser(user : User?) {
        database = FirebaseDatabase.getInstance().reference

        user!!.nombre = editUserName.text.toString()
        user!!.lastname = editUserName.text.toString()
        user!!.email = editUserName.text.toString()
//        user!!.password = editUserName.text.toString()//deberia llamar a resetear la password
        user!!.typeUser = editUserName.text.toString()
        database.child("User").child(user!!.id).setValue(user)
    }

//    private fun changeRole(user: User?): View.OnClickListener? {
//         Toast.makeText(this,"Intentaste cambiar algo ",Toast.LENGTH_LONG).show()
//    }

    private fun setUserInfo(user : User?) {
        editUserName.setText(user!!.nombre)
        editUserLastName.text = user.lastname
        editUserEmail.text = user.email
        editPassword.text = "Cambiar contraseña"
        editPermisos.text = "Gestionar tipo de usuario"
    }

    override fun onDestroy() {
        super.onDestroy()

    }




}