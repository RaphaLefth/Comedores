package com.example.comedor.Presenter.RegisterPresenter


import android.content.Context
import android.content.Intent
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.comedor.View.LoginView.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import java.util.*

class RegisterPresenter(private var context: Context,
                        private var auth: FirebaseAuth,private var database: DatabaseReference) {
    private var TAG : String = "RegisterPresenter"

 fun signUpUser(name:String, lastName: String, email: String, password: String) {
//     val progressdial = ProgressBar(context)
//     progressdial.tooltipText
     auth.createUserWithEmailAndPassword(email,password)
         .addOnCompleteListener(){
                 task ->
             if(task.isSuccessful){
                 val map = hashMapOf<String, Any>()
                 map["nombre"] = name
                 map["lastname"] = lastName
                 map["email"]=email
                 map["password"]=password

                // val user : FirebaseUser? = auth.currentUser //uid
                 //verifyEmail(user)
                 database.child("User").child(task.result!!.user!!.uid)
                     .updateChildren(map)
//                 val userBD = database.child.("User").child(user!!.uid)
//                 userBD.updateChildren(map)

//                 userBD.child("LastName").setValue(lastName)
//                 userBD.child("Email").setValue(email)
//                 userBD.child("Password").setValue(password)
                 action()
             }else{
                 Toast.makeText(context,"Authentication failed",Toast.LENGTH_LONG).show()
             }
         }
 }

    private fun action() {
        context.startActivity(Intent(context,LoginActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?) {

    }
}