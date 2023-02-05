package com.example.roomdatabseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.roomdatabseexample.ViewModels.UserViewModel
import com.example.roomdatabseexample.databinding.ActivityMain2Binding
import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.utils.UserRepository
import com.example.roomdatabseexample.utils.UserViewModelFactory

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var userViewModel: UserViewModel? = null
    private var userRepository: UserRepository? = null
    private var id: Int? = null
    private var imageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        userViewModel =
            ViewModelProvider(this, UserViewModelFactory(UserViewModel(this.application))).get(
                UserViewModel::class.java
            )

        userRepository = UserRepository(this.application)

        id = intent?.getIntExtra("id", 0)

        binding.firstName1.setText(intent?.getStringExtra("firstName") ?: "")



        binding.lastName1.setText(intent?.getStringExtra("lastName") ?: "")


        imageUrl = intent.getStringExtra("image")

        Glide.with(this).asBitmap().load(imageUrl).into(binding.userImage1)


        binding.email1.setText(intent?.getStringExtra("email") ?: "")


        binding.btnUpdate.setOnClickListener {
            updateData()
        }

    }


    private fun updateData() {
        if (binding.firstName1.text.isNullOrEmpty()) {
            Toast.makeText(this, "enter firstName", Toast.LENGTH_SHORT).show()
        } else if (binding.lastName1.text.isNullOrEmpty()) {
            Toast.makeText(this, "enter lastname", Toast.LENGTH_SHORT).show()

        } else if (binding.email1.text.isNullOrEmpty()) {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show()

        } else {

            Log.e("id1", id.toString())
            userViewModel?.updateData(
                Data(
                    imageUrl!!,
                    binding.email1.text.toString(),
                    binding.firstName1.text.toString(),
                    id!!,
                    binding.lastName1.text.toString()
                )
            )

            Log.e("id", id.toString())
            Log.e("imageUrl", imageUrl!!)
            onBackPressed()
        }


    }
}