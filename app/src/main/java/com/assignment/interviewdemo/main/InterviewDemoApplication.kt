package com.assignment.interviewdemo.main

import android.app.Application
import com.google.gson.Gson

class InterviewDemoApplication: Application() {

    companion object {
        private lateinit var gson: Gson

        private lateinit var instance: InterviewDemoApplication

        @JvmStatic
        fun getInstance() = instance

        @JvmStatic
        fun getGsonInstance() = gson
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        gson = Gson()
    }

}