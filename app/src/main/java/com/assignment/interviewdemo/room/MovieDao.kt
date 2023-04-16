package com.assignment.interviewdemo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: MovieTable)


    @Delete
    fun delete(movie: MovieTable)



    @Query("select * from fav_movie")
    fun getAllMovies(): List<MovieTable>
}