package com.example.comedor.View.LoginView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.comedor.View.RecoverView.ForgotPassActivity
import com.example.comedor.Presenter.LoginPresenter.LoginPresenter
import com.example.comedor.R
import com.example.comedor.View.RegisterView.RegistrarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity(), View.OnClickListener{


    private lateinit var txtUser : EditText
    private lateinit var txtPassword : EditText
    private lateinit var progressbar: ProgressBar
    private lateinit var auth: FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : LoginPresenter
    private lateinit var btnLogin : Button
    private lateinit var btnRegister : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
//        Thread.sleep(6000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activty)
        txtUser = findViewById(R.id.txtUserEmail)
        txtPassword = findViewById(R.id.txtUserPassword)
        btnLogin = findViewById(R.id.button)
        btnLogin.setOnClickListener(this)

        btnRegister = findViewById(R.id.txvNewUser)
        btnRegister.setOnClickListener(this)

        progressbar = findViewById(R.id.progressBar2)
        auth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = LoginPresenter(this,auth,mDatabase)


    }

    private fun recoverPassword(){
        startActivity(Intent(this, ForgotPassActivity::class.java))
    }

    private fun register() {
        startActivity(Intent(this, RegistrarActivity::class.java))
    }


    private fun loginUser() {

        val user : String = txtUser.text.toString().trim()
        val password : String = txtPassword.text.toString().trim()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
                progressbar.visibility = View.VISIBLE
                presenter.signInUser(user,password)
        }else{
            txtUser.error = "Required"
            txtPassword.error = "Required"
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            btnLogin.id -> loginUser()
            R.id.txvRecover -> recoverPassword()
            R.id.txvNewUser -> register()
        }

    }
}
