package com.assignment.interviewdemo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieTable::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}