package com.example.roomdatabseexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.services.UserDao

@Database(entities = [Data::class], version = 3)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "userdata.db"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
    }

//    companion object {
////        val DATABASE_NAME = "user_database"
//
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase? {
//
//           return INSTANCE ?: synchronized(this){
//               val instance = Room.databaseBuilder(
//                   context.applicationContext, UserDatabase::class.java, "user_database"
//               ).allowMainThreadQueries()
//                   .build()
//               INSTANCE = instance
//               instance
//           }
//        }
//    }

