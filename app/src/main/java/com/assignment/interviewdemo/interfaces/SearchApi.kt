package com.assignment.interviewdemo.interfaces

import com.assignment.interviewdemo.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface SearchApi {
    @GET
    fun getMovies(@Url url: String): Call<Search>
}