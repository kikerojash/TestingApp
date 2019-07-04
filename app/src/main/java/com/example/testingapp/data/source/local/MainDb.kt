package com.example.testingapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testingapp.model.Main

@Database(entities = [Main::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun mainDao(): MainDao

    companion object {

        @Volatile
        private var INSTANCE: MainDb? = null

        fun getDatabase(context: Context): MainDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MainDb::class.java, "main_Db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}