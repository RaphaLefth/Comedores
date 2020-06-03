package com.example.comedor.Presenter.LoginPresenter
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.comedor.View.MainView.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

 class LoginPresenter(
     private var mContext: Context,
     private var mAuth : FirebaseAuth, private var mDatabase: DatabaseReference) {

     private var TAG : String = ""

     fun signInUser(email: String,password: String){
            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(){
                    task ->
                    if(task.isSuccessful){
                        Log.d(TAG,"signInWithEmail:Success")

                       // mDatabase.child("User").setValue(task.result!!.user!!.uid)

                        action()

                    }else{
                        Log.d(TAG,"signInWithEmail:Failure",task.exception)
                        Toast.makeText(mContext,"Error en la autenticacion",
                            Toast.LENGTH_LONG).show()
                    }
                }

    }

     private fun action() {
         mContext.startActivity(Intent(mContext,MainActivity::class.java))
     }
 }