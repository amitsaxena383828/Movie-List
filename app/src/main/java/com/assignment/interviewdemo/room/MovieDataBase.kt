package com.assignment.interviewdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieTable::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDataBase? = null
        @Synchronized
        fun getInstance(ctx: Context): MovieDataBase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, MovieDataBase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!
        }
    }
}