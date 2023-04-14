package com.assignment.interviewdemo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movie_table")
data class MovieTable(
    @PrimaryKey(autoGenerate = true)
    val Id:Int,
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String,
    val isFavorite:Int
)
