package com.example.testingapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testingapp.ViewModelFactory

fun <T : ViewModel> AppCompatActivity.obtainViewModel(
    viewModelClass: Class<T>,
    viewModelFactory: ViewModelProvider.Factory? = null
) : T {
    return if (viewModelFactory == null) {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)
    } else {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }
}