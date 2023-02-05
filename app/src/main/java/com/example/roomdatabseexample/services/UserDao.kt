package com.example.roomdatabseexample.services

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabseexample.model.Data

@Dao
  interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userList: List<Data>)

    @Update
    fun update(data: Data)

    @Delete
    fun delete(data: Data)

    @Query("SELECT * FROM user ")
    fun getAllData(): LiveData<List<Data>>

//    @Query("SELECT * FROM user")
//    fun deleteAll()

}