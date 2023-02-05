package com.example.roomdatabseexample.services

import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.model.UserListModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {


    @GET("api/users?page=2")
    fun getUSer(): Call<UserListModel>?


}