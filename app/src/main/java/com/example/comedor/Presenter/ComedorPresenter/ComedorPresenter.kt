package com.example.comedor.Presenter.ComedorPresenter

import com.example.comedor.Models.User
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_comedor_screen.view.*

class ComedorPresenter(private var mAuth: FirebaseAuth,
                       private var mDatabase: DatabaseReference,
                       private var context: Context) {
    fun welcomeMsg(view: TextView){

        mDatabase.child("User").child(mAuth.currentUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    var user = p0.getValue(User::class.java)
                //    Toast.makeText(context,"Bienvenido "+ user!!.nombre,Toast.LENGTH_LONG).show()
                    var textView: TextView = view.findViewById(R.id.txtWelcomeComedor) as TextView
                    textView.text = " " +textView.text+ user!!.nombre.toString()
                }

            })
    }
}