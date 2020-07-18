package com.example.comedor.Presenter.RecoverPresenter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.comedor.View.LoginView.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class RecoverPresenter(private var context: Context,
                       private var auth: FirebaseAuth) {

      private fun recoverPassword(email : String){
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                context.startActivity(Intent(context,LoginActivity::class.java))
            }else{
                val error = task.exception!!.message
                Toast.makeText(context,"$error", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context,"Algo salio mal", Toast.LENGTH_LONG).show()
        }
    }

    /**revisa si el email existe antes de enviar la nueva contraseña al email ingresado**/
    fun recoverPasswordwithCheck(email : String){
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            task ->
         if(task.result!!.signInMethods!!.isEmpty()){
             Toast.makeText(context,"Correo invalido",Toast.LENGTH_LONG).show()
         }else{
             recoverPassword(email)
             Toast.makeText(context,"Contraseña enviada",Toast.LENGTH_LONG).show()
         }
        }

    }
}