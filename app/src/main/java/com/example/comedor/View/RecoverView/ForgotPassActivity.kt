package com.example.comedor.View.RecoverView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.example.comedor.Models.User
import com.example.comedor.Presenter.RecoverPresenter.RecoverPresenter
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class ForgotPassActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var txtEmail : EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressbar : ProgressBar
    private lateinit var btnRecover : Button
    private lateinit var presenter : RecoverPresenter
    private  lateinit var email : String
    private  lateinit var btnback : Button
    private  var check : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)
        try {
            check = intent.getSerializableExtra("intent") as Int

        }catch (e : Exception){
            e.printStackTrace()
        }

        auth = FirebaseAuth.getInstance()
        presenter = RecoverPresenter(this,auth,check)

        txtEmail = findViewById(R.id.passForgotE)
        progressbar = findViewById(R.id.progressBar3)
        btnRecover = findViewById(R.id.btnRecover)
        btnback = findViewById(R.id.btnback)

        try {

            email = intent.getSerializableExtra("email") as String

            txtEmail.setText(email)

        }catch (e : Exception){
            e.printStackTrace()
        }
        btnRecover.setOnClickListener(this)
        btnback.setOnClickListener(this)
    }


    private fun recover(){
        val email : String = txtEmail.text.toString()
        if(!TextUtils.isEmpty(email)){
            progressbar.visibility = View.VISIBLE
            presenter.recoverPasswordwithCheck(email)

        }else{
            progressbar.visibility = View.INVISIBLE
            txtEmail.error = "Ingrese un correo valido"
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            btnRecover.id -> recover()
            btnback.id -> onDestroy()
        }
    }
}
