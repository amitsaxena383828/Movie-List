package com.assignment.interviewdemo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.interviewdemo.R
import com.assignment.interviewdemo.adapters.MovieListAdapter
import com.assignment.interviewdemo.databinding.ActivityMainBinding
import com.assignment.interviewdemo.model.MovieList
import com.assignment.interviewdemo.model.Search
import com.assignment.interviewdemo.retrofitHelper.RequestResult
import com.assignment.interviewdemo.utils.Utility
import com.assignment.interviewdemo.viewmodel.MoviesVM
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var moviesVM: MoviesVM
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            makeApiCall(binding.searchEditFrame.text.toString())
        }
        binding.searchEditFrame.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && !binding.searchEditFrame.text.toString()
                    .trim().isEmpty()
            ) {
                hitApi(binding.searchEditFrame.text.toString().trim())
            } else {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.not_empty),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            true
        }
        binding.ivSearchBtn.setOnClickListener {
            makeApiCall(binding.searchEditFrame.text.toString())
        }


    }

    fun makeApiCall(movieName: String) {
        if (!movieName.trim().isEmpty())
            hitApi(binding.searchEditFrame.text.toString().trim())
        else {
            Snackbar.make(
                binding.root,
                resources.getString(R.string.not_empty),
                Snackbar.LENGTH_LONG
            ).show()
            Utility.hideKeyboard(this)
        }
    }


    fun hitApi(movieName: String) {
        Utility.hideKeyboard(this)
        binding.loader.visibility = View.VISIBLE
        moviesVM = ViewModelProvider(this)[MoviesVM::class.java]
        moviesVM.getData(movieName)
            .observe(this, { response ->
                if (response.status == RequestResult.Status.SUCCESS) {
                    val movieData = response.data as Search
                    Log.d("Amit", "Data=${movieData.Search.get(3)}")
                    binding.tvMainTitle.visibility = View.GONE
                    setupRecyclerView(movieData.Search)
                } else {
                    Toast.makeText(
                        this,
                        "Error while retrieving data.\n\n\n\nResponse==\n\n\n" + response.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        binding.loader.visibility = View.GONE
    }

    private fun setupRecyclerView(moviesData: List<MovieList>) {
        binding.rvMoviesList.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MovieListAdapter(this, moviesData)
        binding.rvMoviesList.adapter = adapter
    }
}