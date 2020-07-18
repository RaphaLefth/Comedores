package com.example.comedor.Presenter.LoginPresenter
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.comedor.Models.User
import com.example.comedor.View.AdministratorView.AdministratorActivity
import com.example.comedor.View.ComedorView.ComedorActivity
import com.example.comedor.View.AdministratorView.ReporterActivity
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

                        }else{
                            Log.d(TAG,"signInWithEmail:Failure",task.exception)
                            Toast.makeText(mContext,"No existe este usuario",
                                Toast.LENGTH_LONG).show()
                        }
                    }

    }

     private fun check() {
         mDatabase.child("User").child(mAuth.currentUser!!.uid)
             .addListenerForSingleValueEvent(object : ValueEventListener {
                 override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(mContext,"Este es del Oncancelled",Toast.LENGTH_LONG).show()
                 }

                 override fun onDataChange(p0: DataSnapshot) {
                     if(p0.exists()){
                         val user = p0.getValue(User::class.java)
                         when(user!!.typeUser){
                             "1" -> adminView()
                             "2" -> comedorView()
                             "3" -> reporterView()
                         }
                         //Toast.makeText(mContext,"Bienvenido "+ user!!.nombre+ "del tipo "+ user!!.typeUser,Toast.LENGTH_LONG).show()

                     }else{
                         Toast.makeText(mContext,"NO EXISTE LA BD",Toast.LENGTH_LONG).show()
                     }
                    }

             })
     }

    private fun comedorView() {
        mContext.startActivity(Intent(mContext,
            ComedorActivity::class.java))
    }

    private fun reporterView() {
        mContext.startActivity(Intent(mContext,ReporterActivity::class.java))
    }



     private fun adminView() {
         mContext.startActivity(Intent(mContext,AdministratorActivity::class.java))
     }
 }