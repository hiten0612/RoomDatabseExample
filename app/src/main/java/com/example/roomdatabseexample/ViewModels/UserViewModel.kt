package com.example.roomdatabseexample.ViewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.utils.UserRepository


class UserViewModel(application: Application) : ViewModel() {


    private val userRepository = UserRepository(application)
    val getAllUsers = userRepository.getAllUsers()

    fun insert(list : List<Data>) {

        userRepository.insert(list)
    }

    fun getAllActors() : LiveData<List<Data>>{
        return getAllUsers
    }

    fun updateData(data: Data){
        userRepository.update(data)
    }

    fun deleteData(data: Data){
        userRepository?.delete(data)
    }


}