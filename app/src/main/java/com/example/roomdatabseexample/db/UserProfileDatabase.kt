package com.example.roomdatabseexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabseexample.model.UserAddress
import com.example.roomdatabseexample.model.UserEntity
import com.example.roomdatabseexample.services.UserProfileDao


@Database(entities = [UserEntity::class, UserAddress::class], version = 1)
abstract class UserProfileDatabase : RoomDatabase() {

    abstract fun userProfileDao() : UserProfileDao?

    companion object{

        private var INSTANCE: UserProfileDatabase ? = null

        fun getAppDatabase(context: Context) : UserProfileDatabase?{
            if(INSTANCE == null){

               INSTANCE =  Room.databaseBuilder<UserProfileDatabase>(
                    context.applicationContext, UserProfileDatabase::class.java, "user-profile")
                    .allowMainThreadQueries()
                    .build()


            }
            return INSTANCE
        }
    }

}

