package com.assignment.interviewdemo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assignment.interviewdemo.model.MovieList

@Dao
interface MovieDao {
    @Insert
    fun insert(movie: MovieTable)


    @Delete
    fun delete(movie: MovieTable)



    @Query("select * from fav_movie")
    fun getAllMovies(): List<MovieTable>
}