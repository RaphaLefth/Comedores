package com.example.comedor.View.AdministratorView

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comedor.Adapters.AdminUserPermissionAdapter
import com.example.comedor.Models.User
import com.example.comedor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_comedor.swipeRefresh
import kotlinx.android.synthetic.main.activity_admin_services.*


class AdminUsuarios : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private lateinit var adapter: AdminUserPermissionAdapter
    private var getUsersList : ArrayList<User> = ArrayList()

    private val TAG = "UserList"
    private var userlistReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_services)
        adapter = AdminUserPermissionAdapter(this,getUsersList)
        userlistReference = FirebaseDatabase.getInstance().reference.child("User")
        supportActionBar!!.title = "Gestion de Usuarios"
        onStart()
        setupUserListView()
        swipeUserList.setOnRefreshListener {
            setupUserListView()
        }

    }

    override fun onStart() {
        super.onStart()
        setupUserListView()
        swipeUserList.setOnRefreshListener {
            setupUserListView()
        }
    }
    private fun setupUserListView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        userListRecycler.layoutManager = layoutManager
//        progressBarUserList.visibility= View.VISIBLE
        getData()
    }

    private fun getData() {
        getUsersList.clear()
        getAllUser()

    }

    private fun getAllUser(){
        val dbreference = FirebaseDatabase.getInstance().getReference("User")

        dbreference.addValueEventListener(object  : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@AdminUsuarios, "Error en obtener a los usuarios", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {

                    val post = snapshot.getValue(User::class.java)
                    post!!.id = snapshot.key.toString()
                    getUsersList.add(post)

                    adapter.notifyDataSetChanged()
                }
                //Toast.makeText(this@AdminUsuarios,getUsersList.toString(),Toast.LENGTH_LONG).show()
            }
        })
        userListRecycler.adapter = adapter
        swipeUserList.isRefreshing = false
        adapter.notifyDataSetChanged()
    }

}