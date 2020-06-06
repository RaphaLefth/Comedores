package com.example.comedor.Presenter.PrincipalPresenter

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class PrincipalPresenter(private var mAuth: FirebaseAuth,
                         private var mDatabase: DatabaseReference,
                         private var context: Context) {
    fun msg(){
        mDatabase.child("User").child(mAuth.currentUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    Toast.makeText(context,"Bienvenido "+p0.child("nombre").value,Toast.LENGTH_LONG).show()
                }

            })
    }
}