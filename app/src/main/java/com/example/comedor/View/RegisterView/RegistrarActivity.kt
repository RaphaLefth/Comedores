package com.example.comedor.View.RegisterView

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dev.materialspinner.MaterialSpinner
import com.example.comedor.Presenter.RegisterPresenter.RegisterPresenter
import com.example.comedor.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() , View.OnClickListener, AdapterView.OnItemSelectedListener{

    private lateinit var txtName : EditText
    private lateinit var txtlastName : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var spinner : MaterialSpinner
    private lateinit var progressBar : ProgressBar
    private lateinit var dbReference : DatabaseReference
    private lateinit var database : FirebaseDatabase
    private lateinit var auth : FirebaseAuth
    private lateinit var btnRegister : Button
    private lateinit var presenter : RegisterPresenter
    private lateinit var list : Array<String>
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(3000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        list =  resources.getStringArray(R.array.types)
        txtName = findViewById(R.id.txtName)
        txtlastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
//        progressBar = findViewById(R.id.progressBar)
        btnRegister = findViewById(R.id.btnEnviar)
        spinner = findViewById(R.id.material_spinner)

        spinner.getSpinner().onItemSelectedListener = this
        if (spinner != null) {
//            val adapter = ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, list)
//            spinner.adapter = adapter


            val aa = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(aa)
            spinner.setLabel("Tipos")
//            spinner.setError("Elija un tipo de usuario")

        }
        btnRegister.setOnClickListener(this)
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference //se usa este

        presenter = RegisterPresenter(this,auth,dbReference)


    }

    fun registerNewUser(view: View){
        createNewAccount()
    }
    private fun createNewAccount(){
        val nameUser : String = txtName.text.toString().trim()
        val lastNameUser : String = txtlastName.text.toString().trim()
        val email : String = txtEmail.text.toString().trim()
        val password : String = txtPassword.text.toString().trim()
        val typeUser : String = spinner.getSpinner().selectedItem.toString()
        if(password.length < 6){
            txtPassword.error = "Debe tener al menos 6 caracteres"
        }
        if(!TextUtils.isEmpty(nameUser) && !TextUtils.isEmpty(email) &&!TextUtils.isEmpty(nameUser)
            && !TextUtils.isEmpty(typeUser)){

            presenter.signUpUser(nameUser,lastNameUser,email,password,typeUser)

        }else{
            txtName.error = "Required"
            txtEmail.error = "Required"
            txtlastName.error = "Required"
            txtPassword.error = "Required"
        }


    }
    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this){
            task ->
            if(task.isComplete){
                Toast.makeText(this,"Correo enviado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setProgress(){

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnEnviar -> createNewAccount()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, v: View?, p: Int, p3: Long) {

        Toast.makeText(this@RegistrarActivity,getString(R.string.select_type)+" "+
                        list[p],Toast.LENGTH_LONG).show()
    }



}
