package com.example.roomdatabseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.roomdatabseexample.databinding.ActivityUserDetailsBinding
import com.example.roomdatabseexample.db.UserProfileDatabase
import com.example.roomdatabseexample.model.UserAddress
import com.example.roomdatabseexample.model.UserEntity


class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        saveData()

    }

    private fun saveData() {
        binding.btnSave.setOnClickListener {

            val name = binding.name.text.toString()
            val phone = binding.phone.text.toString()
            val email = binding.email.text.toString()
            val address = binding.address.text.toString()
            val city = binding.city.text.toString()
            val state = binding.state.text.toString()
            val zip = binding.zip.text.toString()


            val userProfileDao = UserProfileDatabase.getAppDatabase(this)?.userProfileDao()
            val userEntity = UserEntity(1, name, phone, email)

            val id = userProfileDao?.insertUserProfile(userEntity)
            Log.e("id", id.toString())


            if(id != null)
            {
                val addressEntity = UserAddress(1, id.toInt(), address, city, state, zip)
                Log.e("add", addressEntity.toString())
                userProfileDao.insertUserAddress(addressEntity)
            }

            else
            {

                Toast.makeText(this, "id is null", Toast.LENGTH_SHORT).show()
            }


            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }

    }

}