package com.example.comedor.Presenter.AdminPresenter

import android.content.Context
import android.widget.Toast
import com.example.comedor.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AdministratorPresenter(private var context: Context,
                             private var mDatabase : DatabaseReference,
                             private var mAuth:FirebaseAuth) {

//    fun welcomeMsg(){
//        mDatabase.child("User").child(mAuth!!.currentUser!!.uid
//        ).addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onCancelled(dataSnapshot: DatabaseError) {
//
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                var u : User? = dataSnapshot.getValue(User::class.java)
//                Toast.makeText(context,"Bienvenido "+u!!.nombre
//                    , Toast.LENGTH_LONG).show()
//
//            }
//
//        })
//    }

}