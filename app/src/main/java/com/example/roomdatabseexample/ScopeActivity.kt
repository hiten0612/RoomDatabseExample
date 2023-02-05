package com.example.roomdatabseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.roomdatabseexample.R
import com.example.roomdatabseexample.databinding.ActivityScopeBinding

class ScopeActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityScopeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scope)


    }

}