package com.assignment.interviewdemo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movie")
data class MovieTable(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    @PrimaryKey(autoGenerate = false)
    val imdbID: String

)
