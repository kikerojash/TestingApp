package com.example.testingapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testingapp.data.source.MainRepository
import com.example.testingapp.data.source.local.MainDb
import com.example.testingapp.data.source.remote.MainServiceFactory

class ViewModelFactory private constructor(
    private val application: Application,
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                  isAssignableFrom(MainViewModel::class.java) ->
                      MainViewModel(application, mainRepository)
                /*  isAssignableFrom(MatchViewModel::class.java) ->
                      MatchViewModel(application, sportRepository)
                  isAssignableFrom(TeamViewModel::class.java) ->
                      TeamViewModel(application, sportRepository)
                  isAssignableFrom(PlayerViewModel::class.java) ->
                      PlayerViewModel(application, sportRepository)
                  isAssignableFrom(SearchMatchViewModel::class.java) ->
                      SearchMatchViewModel(application, sportRepository)
                  isAssignableFrom(SearchTeamViewModel::class.java) ->
                      SearchTeamViewModel(application, sportRepository)*/
                  else ->
                      error("Invalid View Model class")
            }
        } as T


    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                ViewModelFactory(
                    application,
                    MainRepository.getInstance(
                        MainDb.getDatabase(application.applicationContext),
                        MainServiceFactory.getService()
                    )
                ).also { INSTANCE = it }
            }
        }
    }
}