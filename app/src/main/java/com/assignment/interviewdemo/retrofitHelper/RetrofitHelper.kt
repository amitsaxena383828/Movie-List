package com.assignment.interviewdemo.retrofitHelper

import com.assignment.interviewdemo.constants.UrlConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}