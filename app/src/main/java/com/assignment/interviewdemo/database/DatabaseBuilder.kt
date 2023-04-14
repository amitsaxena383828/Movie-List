package com.assignment.interviewdemo.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var INSTANCE: MovieDatabase? = null
    fun getInstance(context: Context): MovieDatabase {
        if (INSTANCE == null) {
            synchronized(MovieDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
}