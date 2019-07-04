package com.example.testingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.testingapp.base.AbsentLiveData
import com.example.testingapp.base.Resource
import com.example.testingapp.data.source.MainRepository
import com.example.testingapp.model.Main

class MainViewModel(ctx: Application, private val mainRepository: MainRepository) : AndroidViewModel(ctx) {


    private val idEvent = MutableLiveData<String>()

    val matchDetail: LiveData<Resource<Main>> = Transformations.switchMap(idEvent) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            mainRepository.getEventDetail(id)
        }
    }



    fun initData(idEvent: String) {
        this.idEvent.value = idEvent
    }
}