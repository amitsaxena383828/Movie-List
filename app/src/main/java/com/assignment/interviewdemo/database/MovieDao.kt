package com.assignment.interviewdemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.assignment.interviewdemo.model.MovieList

@Dao
interface MovieDao {
    @Query("SELECT * FROM fav_movie_table")
     fun getAll(): List<MovieList>

    @Insert
     fun insert(Movie: MovieList)

    @Delete
     fun delete(Movie: MovieTable)
}