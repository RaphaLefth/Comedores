package com.example.comedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    private lateinit var txtUser : EditText
    private lateinit var txtPassword : EditText
    private lateinit var progressbar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(6000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activty)
        txtUser = findViewById(R.id.txtUserEmail)
        txtPassword = findViewById(R.id.txtUserPassword)
        progressbar = findViewById(R.id.progressBar2)
        auth = FirebaseAuth.getInstance()
    }

    fun recoverPassword(view: View){
        startActivity(Intent(this,ForgotPassActivity::class.java))
    }

    fun register(view: View) {
        startActivity(Intent(this,RegistrarActivity::class.java))
    }
    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {

        val user : String = txtUser.text.toString()
        val password : String = txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressbar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(this){
                    task ->
                if(task.isComplete){
                    action()
                }else{
                    Toast.makeText(this,"Error en la autenticacion", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}
