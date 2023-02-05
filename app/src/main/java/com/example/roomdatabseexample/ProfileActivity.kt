package com.example.roomdatabseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.roomdatabseexample.databinding.ActivityProfileBinding
import com.example.roomdatabseexample.db.UserProfileDatabase

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_profile)

        val userProfileDao = UserProfileDatabase.getAppDatabase(this.applicationContext)?.userProfileDao()
        val list = userProfileDao?.getAllUserWithAddress()



        list?.forEach {

            binding.userName.append(it.name)
            binding.userPhone.append(it.phone)
            binding.userEmail.append(it.email)
            binding.userAdd.append(it.address)
            binding.userCity.append(it.city)
            binding.userState.append(it.state)
            binding.userZip.append(it.zip)

        }
    }
}