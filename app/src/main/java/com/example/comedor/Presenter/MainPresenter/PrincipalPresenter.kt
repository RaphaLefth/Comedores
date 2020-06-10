package com.example.comedor.Presenter.MainPresenter

import android.content.Context
import android.widget.Toast
import com.example.comedor.Models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PrincipalPresenter(private var context: Context,
                         private var mDatabase : DatabaseReference,
                         private var mAuth:FirebaseAuth) {

    fun welcomeMsg(){
        mDatabase.child("User").child(mAuth!!.currentUser!!.uid
        ).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var u : UserModel? = dataSnapshot.getValue(UserModel::class.java)
//                var user : UserModel = dataSnapshot.value as UserModel
//                +dataSnapshot.child("nombre")
                Toast.makeText(context,"Bienvenido "+u!!.nombre
                    , Toast.LENGTH_LONG).show()

            }

        })
    }

}