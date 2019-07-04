package com.example.testingapp.data.source

import androidx.lifecycle.LiveData
import com.example.testingapp.base.ApiResponse
import com.example.testingapp.base.ContextProviders
import com.example.testingapp.base.NetworkBoundResource
import com.example.testingapp.base.Resource
import com.example.testingapp.data.source.local.MainDao
import com.example.testingapp.data.source.local.MainDb
import com.example.testingapp.data.source.remote.MainService
import com.example.testingapp.data.source.remote.json.SchedulesResponse
import com.example.testingapp.model.Main

class MainRepository(
    private val db: MainDb,
    private val mainDao: MainDao,
    private val mainService: MainService,
    private val coroutineContext: ContextProviders
) {


    fun getEventDetail(matchId: String): LiveData<Resource<Main>> {
        return object : NetworkBoundResource<Main, SchedulesResponse>(coroutineContext) {
            override fun saveCallResult(item: SchedulesResponse) {
                item.events?.let { matches ->
                    matches.forEach { match ->
                        match?.let {
                            if (match.isNextMatch()) {
                                match.matchType = "type_next_match"
                            } else {
                                match.matchType = "type_prev_match"
                            }
                        }
                    }
                    db.runInTransaction {
                        mainDao.saveMatches(matches)
                    }

                }
            }

            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = mainService.getMatchDetail(matchId)

            override fun shouldFetch(data: Main?): Boolean = data == null

            override fun loadFromDb(): LiveData<Main> = mainDao.getMatchDetail(matchId)

        }.asLiveData()
    }


    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(
            mainDb: MainDb,
            sportService: MainService
        ): MainRepository = INSTANCE
            ?: synchronized(MainRepository::class.java) {
                MainRepository(
                    mainDb,
                    mainDb.mainDao(),
                    sportService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }


}