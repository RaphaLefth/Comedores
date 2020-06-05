package com.example.comedor.Presenter.RecoverPresenter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.comedor.View.LoginView.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RecoverPresenter(private var context: Context,
                       private var auth: FirebaseAuth) {

     fun recoverPassword(email : String){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(){
            task ->
            if(task.isSuccessful){
                //progressbar.visibility = View.VISIBLE
                action()
            }else{
                Toast.makeText(context,"Error al enviar email", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun action(){
        context.startActivity(Intent(context,LoginActivity::class.java)
        )
    }
}