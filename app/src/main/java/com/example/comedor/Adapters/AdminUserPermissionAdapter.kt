package com.example.comedor.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.comedor.Models.User
import com.example.comedor.R
import com.example.comedor.View.AdministratorView.AdminEditUserData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.admincomedorlayout.view.*
import kotlinx.android.synthetic.main.row_layout_users.view.*

class AdminUserPermissionAdapter(var context: Context, private var arrayList: ArrayList<User>) :
    RecyclerView.Adapter<AdminUserPermissionAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val viewholder = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_users
        ,parent,false)
        return myViewHolder(viewholder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val userListData = arrayList[position]
        holder.setData(userListData,position)
    }


    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var currentUserList: User? = null
        private var currentPosition: Int = 0



        init {
            //click sobre el recycler
            itemView.setOnClickListener{
                val i= Intent(context, AdminEditUserData::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val userPosition = arrayList[currentPosition]

                i.putExtra("getUserInf",userPosition)
                
                context.startActivity(i)
            }
//
//            itemView.delete_data.setOnClickListener {
//
//                deleteUser(currentUserList!!.id)
//
//                arrayList.removeAt(currentPosition)
//
//                notifyDataSetChanged()
//            }
            //editar
//            itemView.edite_data.setOnClickListener {
//
//                val i= Intent(context, AdminEditUserData::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                val userPosition = arrayList[currentPosition]
//
//                i.putExtra("getlistData",userPosition)
//                context.startActivity(i)
//            }

        }
        fun setData(userlistdataData: User?, pos: Int) {


            userlistdataData?.let {
//                itemView.userPhoto.text = userlistdataData.photo
                itemView.userName.text = userlistdataData.nombre
                itemView.userEmail.text = userlistdataData.email
            }
            this.currentUserList = userlistdataData
            this.currentPosition = pos
        }
    }

    private fun deleteUser(idUser: String) {
        val dbReference = FirebaseDatabase.getInstance().
                                            getReference("User").child(idUser)
        dbReference.removeValue()
        Toast.makeText(context,"Borrado exitosamente",Toast.LENGTH_SHORT).show()

    }
}