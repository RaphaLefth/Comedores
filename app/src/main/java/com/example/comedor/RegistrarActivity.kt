package com.example.comedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
<<<<<<< HEAD
=======
import com.example.comedor.View.LoginView.LoginActivity
>>>>>>> 66e70d99c360d19b8f650abd34d1e51b3e0c1993
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() {

    private lateinit var txtName : EditText
    private lateinit var txtlastName : EditText
    private lateinit var txtEmail : EditText
    private lateinit var txtPassword : EditText
    private lateinit var progressBar : ProgressBar
    private lateinit var dbReference : DatabaseReference
    private lateinit var database : FirebaseDatabase
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        txtName = findViewById(R.id.txtName)
        txtlastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)
        FirebaseApp.initializeApp(this)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")


    }

    fun registerNewUser(view: View){
        createNewAccount()
    }
    private fun createNewAccount(){
        val nameUser : String = txtName.text.toString()
        val lastNameUser : String = txtlastName.text.toString()
        val email : String = txtEmail.text.toString()
        val password : String = txtPassword.text.toString()

<<<<<<< HEAD
=======
//       val sd = User("",nameUser,lastNameUser,email,password)
>>>>>>> 66e70d99c360d19b8f650abd34d1e51b3e0c1993


        if(!TextUtils.isEmpty(nameUser) && !TextUtils.isEmpty(lastNameUser)
            && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                progressBar.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isComplete){
                            val user : FirebaseUser? = auth.currentUser
                            verifyEmail(user)
                            val userBD = dbReference.child(user!!.uid)
                            userBD.child("Name").setValue(nameUser)
                            userBD.child("LastName").setValue(lastNameUser)
                            action()
                        }
                    }
        }
    }
    private fun action(){
<<<<<<< HEAD
        startActivity(Intent(this,LoginActivity::class.java))
=======
        startActivity(Intent(this,
            LoginActivity::class.java))
>>>>>>> 66e70d99c360d19b8f650abd34d1e51b3e0c1993
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

}
