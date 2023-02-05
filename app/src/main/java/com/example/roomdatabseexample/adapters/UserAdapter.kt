package com.example.roomdatabseexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdatabseexample.R
import com.example.roomdatabseexample.databinding.UserListBinding
import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.services.Listeners

class UserAdapter(val context: Context, private val listeners: Listeners) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var binding: UserListBinding
    private var uList = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

         binding = UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.uFirstName.text = uList[position].first_name
        holder.uLastName.text = uList[position].last_name
        holder.uEmail.text = uList[position].email
        Glide.with(context).load(uList[position].avatar).into(holder.user_img)

        binding.root.setOnClickListener {
            listeners.onItemClick(listeners,position)
        }

        holder.userDelete.setOnClickListener{
            listeners.onDeleteClick(listeners,position)
        }


//        with(holder) {
//            with(uList[position]) {
//
//                binding.uEmail.text = this.email
//                binding.uFirstName.text = this.first_name
//                binding.uLastName.text = this.last_name
//
//
//                binding.root.setOnClickListener{
//                    listeners.onItemClick(listeners,position)
//                }
//
//
//            }
//
//
//        }

    }


    override fun getItemCount(): Int {

        return uList.size
    }

    fun getAllUsers(uList : List<Data>){
        this.uList = uList as ArrayList<Data>



    }

    class ViewHolder(binding: UserListBinding) : RecyclerView.ViewHolder(binding.root) {

        val user_img: ImageView = itemView.findViewById(R.id.user_img)
        val userDelete: ImageView = itemView.findViewById(R.id.userDelete)
        val uEmail: TextView = itemView.findViewById(R.id.uEmail)
        val uFirstName: TextView = itemView.findViewById(R.id.uFirstName)
        val uLastName: TextView = itemView.findViewById(R.id.uLastName)


    }


}