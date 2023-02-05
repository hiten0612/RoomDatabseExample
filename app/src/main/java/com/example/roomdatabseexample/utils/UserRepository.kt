package com.example.roomdatabseexample.utils

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roomdatabseexample.db.UserDatabase
import com.example.roomdatabseexample.model.Data

class UserRepository(application: Application) {

    private val userDatabase = UserDatabase.getDatabase(application)

    private val getAllUser = userDatabase?.userDao()?.getAllData()


    fun insert(userList: List<Data>) {


        InsertAsyncTask(userDatabase!!).execute(userList)

    }

    fun update(data : Data){

        return userDatabase?.userDao()?.update(data)!!

    }

    fun delete(data: Data)
    {
        return userDatabase?.userDao()?.delete(data)!!
    }


    fun getAllUsers(): LiveData<List<Data>> {

        return getAllUser!!
    }

    class InsertAsyncTask(userDatabase: UserDatabase) : AsyncTask<List<Data>, Void, Void>() {


        private val userDao = userDatabase.userDao()


        override fun doInBackground(vararg params: List<Data>?): Void? {

            userDao.insert(params[0]!!)
            return null


        }


    }


}