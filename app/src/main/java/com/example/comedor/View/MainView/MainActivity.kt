package com.example.comedor.View.MainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comedor.Presenter.PrincipalPresenter.PrincipalPresenter
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDatabase : DatabaseReference
    private lateinit var presenter : PrincipalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
//		Thread.sleep(3500)
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        presenter = PrincipalPresenter(mAuth,mDatabase,this)
        presenter.msg()        


//        mDatabase.run {
//            child("User").child(mAuth!!.currentUser!!.uid).
//            addListenerForSingleValueEvent(object : ValueEventListener{
//                override fun onCancelled(p0: DatabaseError) {
//
//                }
//
//                override fun onDataChange(p0: DataSnapshot) {
//                }
//
//            })
//        }
    }



}
