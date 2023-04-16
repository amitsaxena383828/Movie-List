package com.assignment.interviewdemo.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.interviewdemo.constants.Constants
import com.assignment.interviewdemo.databinding.ActivityMovieDetailBinding
import com.assignment.interviewdemo.model.MovieList
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var bindingMovieDetail: ActivityMovieDetailBinding
    private lateinit var movieDetail: MovieList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMovieDetail = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(bindingMovieDetail.root)
        getMovieData()
        setData()
    }

    private fun getMovieData() {
        val jsonString = intent.getStringExtra(Constants.EXTRA_PARAM_MOVIE_DETAIL)
        movieDetail =
            InterviewDemoApplication.getGsonInstance().fromJson(jsonString, MovieList::class.java)
        bindingMovieDetail.movieDetail=movieDetail
    }

    @SuppressLint("SetTextI18n")
    fun setData() {
        Glide.with(this).load(movieDetail.Poster).into(bindingMovieDetail.ivMovieBanner)
    }
}