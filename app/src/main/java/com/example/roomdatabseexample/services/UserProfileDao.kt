package com.example.roomdatabseexample.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabseexample.model.UserAddress
import com.example.roomdatabseexample.model.UserEntity
import com.example.roomdatabseexample.model.UserWithAddress


@Dao
interface UserProfileDao {

    @Query("SELECT * FROM user_profile ORDER BY id desc")
    fun getUserProfileData():List<UserEntity>?

    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(user : UserEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserAddress(user: UserAddress)

    @Query("SELECT * FROM user_profile inner join UserAddress on user_profile.id = UserAddress.user_id")
    fun getAllUserWithAddress() : List<UserWithAddress>?
}