package com.example.testingapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testingapp.model.Main

@Dao
interface MainDao {

    @Query("SELECT * FROM matches WHERE idEvent = :matchId")
    fun getMatchDetail(matchId: String): LiveData<Main>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMatches(matches: List<Main?>)
}