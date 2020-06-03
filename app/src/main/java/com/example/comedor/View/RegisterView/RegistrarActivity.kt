package com.example.comedor.View.RegisterView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.comedor.Presenter.RegisterPresenter.RegisterPresenter
import com.example.comedor.R
import com.example.comedor.View.LoginView.LoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var txtName : EditText
    private lateinit var txtlastName : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var progressBar : ProgressBar
    private lateinit var dbReference : DatabaseReference
    private lateinit var database : FirebaseDatabase
    private lateinit var auth : FirebaseAuth
    private lateinit var btnRegister : Button
    private lateinit var presenter : RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        txtName = findViewById(R.id.txtName)
        txtlastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)
        btnRegister = findViewById(R.id.btnEnviar)
        btnRegister.setOnClickListener(this)
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference

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

//       val sd = User("",nameUser,lastNameUser,email,password)
        presenter.signUpUser(nameUser,lastNameUser,email,password)

//        if(!TextUtils.isEmpty(nameUser) && !TextUtils.isEmpty(lastNameUser)
//            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
//                progressBar.visibility = View.VISIBLE
//                auth.createUserWithEmailAndPassword(email,password)
//                    .addOnCompleteListener(this){
//                        task ->
//                        if(task.isComplete){
//                            val user : FirebaseUser? = auth.currentUser
//                            verifyEmail(user)
//                            val userBD = dbReference.child(user!!.uid)
//                            userBD.child("Name").setValue(nameUser)
//                            userBD.child("LastName").setValue(lastNameUser)
//                            action()
//                        }
//                    }
//        }
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

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnEnviar -> createNewAccount()
        }
    }

}
