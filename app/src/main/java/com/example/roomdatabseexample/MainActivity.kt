package com.example.roomdatabseexample

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabseexample.ViewModels.UserViewModel
import com.example.roomdatabseexample.adapters.UserAdapter
import com.example.roomdatabseexample.databinding.ActivityMainBinding
import com.example.roomdatabseexample.model.Data
import com.example.roomdatabseexample.model.UserListModel
import com.example.roomdatabseexample.services.Listeners
import com.example.roomdatabseexample.utils.ApiClient
import com.example.roomdatabseexample.utils.UserRepository
import com.example.roomdatabseexample.utils.UserViewModelFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), Listeners {

    private var userViewModel: UserViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var userAdapter: UserAdapter? = null

    private var uList = ArrayList<Data>()
    private var userRepository: UserRepository? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       binding.edtFirst.addTextChangedListener(object : TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

               binding.tv.setCharacterDelay(150)
               binding.tv.animateText(binding.edtFirst.text.toString())           }

           override fun afterTextChanged(p0: Editable?) {
           }

       })



        //   binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this@MainActivity, this)
        userViewModel =
            ViewModelProvider(this, UserViewModelFactory(UserViewModel(this.application))).get(
                UserViewModel::class.java
            )
        userRepository = UserRepository(this.application)

        userViewModel?.getAllActors()?.observe(this) {
            userAdapter?.getAllUsers(it as ArrayList<Data>)
            //  binding.recyclerView.adapter = userAdapter
//            Toast.makeText(this@MainActivity, "Working Fine", Toast.LENGTH_SHORT).show()
            Log.e("main", it.toString())
            uList = it as ArrayList<Data>
        }

        ApiClient.getRetroFitClient().getUSer()?.enqueue(object :
            retrofit2.Callback<UserListModel> {
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                if (response.isSuccessful) {

                    Log.e("response", response.body()?.data.toString())
                    Log.e(
                        "====jsonResponse===",
                        "response 33: " + Gson().toJson(response.body()?.data)
                    )

                    // Log.v("====response===","${response.body()}")

                    userViewModel?.insert(response.body()?.data!!)
                    //  userRepository?.insert(response.body()?.data!!)
                    //   uList = response.body()?.data as ArrayList<Data>
                }


            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {

                //Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

            }

        })

    }


    override fun onItemClick(listener: Any, position: Int) {

        val intent = Intent(this@MainActivity, MainActivity2::class.java)
        intent.putExtra("id", uList[position].id)
        intent.putExtra("firstName", uList.get(position).first_name)
        intent.putExtra("lastName", uList.get(position).last_name)
        intent.putExtra("email", uList.get(position).email)
        intent.putExtra("image", uList.get(position).avatar)
        startActivity(intent)

    }

    override fun onDeleteClick(listener: Any, position: Int) {

        val data = uList[position]
        val builder = AlertDialog.Builder(this)


        builder.setMessage("Are You Sure Want to delete?")
        //builder.setCancelable(false);

        //builder.setCancelable(false);
        builder.setPositiveButton("Yes", fun(dialog: DialogInterface, id: Int) {
            dialog.cancel()


            userViewModel?.deleteData(
                Data(
                    data.avatar,
                    data.email,
                    data.first_name,
                    data.id,
                    data.last_name
                )
            )
            Toast.makeText(this, "Delete SuccessFully", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        })

        builder.setNegativeButton(
            "No", fun(dialog: DialogInterface, id: Int) {
                dialog.cancel()
            }
        )

        val alert11 = builder.create()
        alert11.show()


    }

}