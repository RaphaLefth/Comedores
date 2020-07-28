package com.example.comedor.View.AdministratorView

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.get
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Models.User
import com.example.comedor.R
import com.example.comedor.View.RecoverView.ForgotPassActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_admin_edit_user_data.*
import java.lang.Exception

class AdminEditUserData : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var dbReference: DatabaseReference
    private lateinit var mauth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var spinner: MaterialSpinner
    private lateinit var list : Array<String>
    private lateinit var user : User
    private val KEY_NAME = "user_name"
    private val KEY_LASTNAME = "user_lastn"
    private val KEY_EMAIL = "user_email"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        val u : User?
        outState.putSerializable("usersaved",user)
        outState.putString(KEY_NAME,editUserName.text.toString())
        outState.putString(KEY_LASTNAME,editUserLastName.text.toString())
        outState.putString(KEY_EMAIL,editUserEmail.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.putSerializable("usersaved",user)
        savedInstanceState.putString(KEY_NAME,editUserName.text.toString())
        savedInstanceState.putString(KEY_LASTNAME,editUserLastName.text.toString())
        savedInstanceState.putString(KEY_EMAIL,editUserEmail.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_user_data)
        list =  resources.getStringArray(R.array.types)
        spinner =findViewById(R.id.sp_edit_perm)
        if(savedInstanceState !=null){
            user = savedInstanceState.getSerializable("usersaved") as User
        }else{
            try {
                user = (intent.getSerializableExtra("getUserInf") as User?)!!

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        spinner.getSpinner().onItemSelectedListener = this
        if (spinner!=null){
            val ad = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
            ad.setDropDownViewResource(android.R.layout.select_dialog_item)
            spinner.setAdapter(ad)
            spinner.setLabel("Tipo")
        }
//        Toast.makeText(this,user.toString(),Toast.LENGTH_LONG).show()
        setUserInfo(user)

        editPermisos.setOnClickListener {
            spinner.visibility = View.VISIBLE
//            Toast.makeText(applicationContext,"No disponible por el momento",Toast.LENGTH_LONG).show()
            editPerm(user)
            /**
             *
            //            val db = AlertDialog.Builder(this,R.style.Widget_AppCompat_ButtonBar_AlertDialog)
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)
            val v : View  = layoutInflater.inflate(R.layout.change_type_dialog_spinner,null)
            dialog.setTitle("Elija")
            //            var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,
            //            android.R.layout.simple_spinner_dropdown_item,resources.)
             *
             * **/

        }

        btnSaveChanges.setOnClickListener{
            updateUser(user)
            editPerm(user)
            spinner.visibility = View.GONE
           disableData()
        }
        btnEnableEditUser.setOnClickListener {
            setData()
        }
        editPassword.setOnClickListener {
            resetPassword(user)
        }
    }

    private fun resetPassword(user: User?) {
        val current = editUserEmail.text.toString()
        val i = Intent(applicationContext, ForgotPassActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.putExtra("email",current)
        i.putExtra("intent",1)
        applicationContext.startActivity(i)
        onResume()

    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(this,"En resumen..sin datos :c",Toast.LENGTH_LONG).show()

    }


    private fun setData() {
        editUserName.isEnabled = true
        editUserLastName.isEnabled = true
        editUserEmail.isEnabled = false
        editPermisos.isEnabled = true
        btnSaveChanges.isEnabled = true
    }

    private fun disableData(){
        editUserName.isEnabled = false
        editUserLastName.isEnabled = false
        editUserEmail.isEnabled = false
        editPermisos.isEnabled = false
        btnSaveChanges.isEnabled = false
        spinner.visibility = View.GONE
    }
    private fun editPerm(user: User?)
    {
        btnSaveChanges.isEnabled = true
        database = FirebaseDatabase.getInstance()
        mauth = FirebaseAuth.getInstance()
        dbReference = database.reference
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@AdminEditUserData,"Error al actualizar los datos",Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                val id = user!!.id.toString()
//                user.typeUser = spinner.getSpinner().selectedItem.toString()
                val map = hashMapOf<String,Any>()
                map["nombre"] = editUserName.text.toString()
                map["id"] = id.toString()
                map["lastname"] = editUserLastName.text.toString()
                map["email"]= editUserEmail.text.toString()
                map["password"]= user.password
                map["typeUser"] = c(user.typeUser)
                dbReference.child("User").child(id).updateChildren(map)


            }

        })

    }

    private fun c(s : String): String {
        var r = "0"
        when(s){
            "Administrador"-> r="1"
            "Comedor"-> r="2"
            "Reporte"->r="3"
        }
        return r
    }

    private fun updateUser(user : User?) {
        database = FirebaseDatabase.getInstance()
//        dbReference = FirebaseDatabase.getInstance().reference
        mauth =FirebaseAuth.getInstance()
        dbReference=database.reference //this

        dbReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@AdminEditUserData,"Error al actualizar los datos",Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                val id = user!!.id.toString()
                val map = hashMapOf<String,Any>()
                map["nombre"] = editUserName.text.toString()
                map["id"] = id.toString()
                map["lastname"] = editUserLastName.text.toString()
                map["email"]= editUserEmail.text.toString()
                map["password"]= user.password
                map["typeUser"] = user.typeUser

                dbReference.child("User").child(id).updateChildren(map)
            }

        })
    disableData()
        Toast.makeText(this@AdminEditUserData,"Usuario actualizado",Toast.LENGTH_SHORT).show()
    }

//    private fun changeRole(user: User?): View.OnClickListener? {
//         Toast.makeText(this,"Intentaste cambiar algo ",Toast.LENGTH_LONG).show()
//    }

    private fun setUserInfo(user : User?) {
        editUserName.setText(user!!.nombre)
        editUserLastName.setText(user.lastname)
        editUserEmail.setText(user.email)
        editPassword.text = "Cambiar contraseña"
        editPermisos.text = "Gestionar tipo de usuario"


        //disable
       disableData()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        user.typeUser = spinner.getSpinner().selectedItem.toString()
    }


}