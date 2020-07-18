package com.example.comedor.View.RecoverView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.example.comedor.Presenter.RecoverPresenter.RecoverPresenter
import com.example.comedor.R
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

        auth = FirebaseAuth.getInstance()
        presenter = RecoverPresenter(this,auth)

        txtEmail = findViewById(R.id.passForgotE)
        progressbar = findViewById(R.id.progressBar3)
        btnRecover = findViewById(R.id.btnRecover)
        btnRecover.setOnClickListener(this)
    }

    private fun recover(){

        val email : String = txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            presenter.recoverPasswordwithCheck(email)

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
