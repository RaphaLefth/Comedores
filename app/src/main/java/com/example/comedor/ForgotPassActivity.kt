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

class ForgotPassActivity : AppCompatActivity() {

    private lateinit var txtEmail : EditText

    private lateinit var auth: FirebaseAuth

    private lateinit var progressbar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        txtEmail = findViewById(R.id.passForgotE)
        auth = FirebaseAuth.getInstance()
        progressbar = findViewById(R.id.progressBar3)
    }

    fun send(view: View) {
        val email : String = txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    progressbar.visibility = View.VISIBLE
                        startActivity(Intent(this,LoginActivity::class.java))
                }else{
                    Toast.makeText(this,"Error al enviar email", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}
