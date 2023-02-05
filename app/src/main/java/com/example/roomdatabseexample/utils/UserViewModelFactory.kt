package com.example.roomdatabseexample.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class UserViewModelFactory(internal var viewModel: ViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {

        return viewModel as T
    }
}