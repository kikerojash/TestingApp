package com.example.testingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.testingapp.base.Resource
import com.example.testingapp.base.obtainViewModel
import com.example.testingapp.model.Main


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        viewModel = obtainViewModel()
       // var idEvent = "602139"
        viewModel.initData("602139")
        viewModel.matchDetail.observe(this, Observer { res -> setupMatchDetail(res) })
    }

    private fun setupMatchDetail(resource: Resource<Main>?) {

    }


    private fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
