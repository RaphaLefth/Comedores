package com.example.comedor.View.RecoverView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.comedor.Presenter.RecoverPresenter.RecoverPresenter
import com.example.comedor.R
import com.example.comedor.View.LoginView.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPassActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var txtEmail : EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressbar : ProgressBar
    private lateinit var btnRecover : Button
    private lateinit var presenter : RecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        txtEmail = findViewById(R.id.passForgotE)
        auth = FirebaseAuth.getInstance()
        progressbar = findViewById(R.id.progressBar3)
        btnRecover = findViewById(R.id.btnRecover)
        btnRecover.setOnClickListener(this)
        presenter = RecoverPresenter(this,auth)
    }

    fun recover() {
        val email : String = txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            presenter.recoverPassword(email)
            //            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
//                task ->
//                if(task.isSuccessful){
//                    progressbar.visibility = View.VISIBLE
//                        startActivity(Intent(this,
//                            LoginActivity::class.java))
//                }else{
//                    Toast.makeText(this,"Error al enviar email", Toast.LENGTH_LONG).show()
//
//                }
//            }
        }else{
            txtEmail.error = "Ingrese un correo valido"
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            btnRecover.id -> recover()
        }
    }
}
