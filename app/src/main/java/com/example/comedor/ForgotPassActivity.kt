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
<<<<<<< HEAD
                        startActivity(Intent(this,LoginActivity::class.java))
=======
                        startActivity(Intent(this,
                            LoginActivity::class.java))
>>>>>>> 66e70d99c360d19b8f650abd34d1e51b3e0c1993
                }else{
                    Toast.makeText(this,"Error al enviar email", Toast.LENGTH_LONG).show()

                }
            }
        }
    }
}
