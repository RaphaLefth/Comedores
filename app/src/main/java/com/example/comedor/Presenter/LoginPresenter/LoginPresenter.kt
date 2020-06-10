package com.example.comedor.Presenter.LoginPresenter
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.comedor.Models.User
import com.example.comedor.View.MainView.AdministratorActivity
import com.example.comedor.View.MainView.ComedorActivity
import com.example.comedor.View.MainView.ReporterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

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

                       //check typeUser
                        check()
//                        adminView()
                    }else{
                        Log.d(TAG,"signInWithEmail:Failure",task.exception)
                        Toast.makeText(mContext,"Error en la autenticacion",
                            Toast.LENGTH_LONG).show()
                    }
                }

    }

     private fun check() {
         mDatabase.child("User").child(mAuth.currentUser!!.uid)
             .addListenerForSingleValueEvent(object : ValueEventListener {
                 override fun onCancelled(p0: DatabaseError) {

                 }

                 override fun onDataChange(p0: DataSnapshot) {
                     var user = p0.getValue(User::class.java)
                     when(user!!.type){
                         "1" -> adminView()
                         "2" -> comedorView()
                         "3" -> reporterView()
                     }
                     Toast.makeText(mContext,"Bienvenido "+ user!!.nombre,Toast.LENGTH_LONG).show()
                 }

             })
     }

    private fun comedorView() {
        mContext.startActivity(Intent(mContext,ComedorActivity::class.java))
    }

    private fun reporterView() {
        mContext.startActivity(Intent(mContext,ReporterActivity::class.java))
    }



     private fun adminView() {
         mContext.startActivity(Intent(mContext,AdministratorActivity::class.java))
     }
 }