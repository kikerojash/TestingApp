package com.example.testingapp.data.source.remote

import androidx.lifecycle.LiveData
import com.example.testingapp.base.ApiResponse
import com.example.testingapp.data.source.remote.json.SchedulesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId: String): LiveData<ApiResponse<SchedulesResponse>>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String) : LiveData<ApiResponse<SchedulesResponse>>
}