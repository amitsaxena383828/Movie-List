package com.assignment.interviewdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.interviewdemo.constants.Constants
import com.assignment.interviewdemo.interfaces.SearchApi
import com.assignment.interviewdemo.model.Search
import com.assignment.interviewdemo.retrofitHelper.RequestResult
import com.assignment.interviewdemo.retrofitHelper.RetrofitHelper
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesVM : ViewModel() {
    var stringMutableLiveData = MutableLiveData<RequestResult<Any?>>()
    fun getData(movieName: String): LiveData<RequestResult<Any?>> {
        stringMutableLiveData = MutableLiveData()
        loadData(movieName)
        return stringMutableLiveData
    }

    fun loadData(movieName: String) {
        viewModelScope.launch {
            val call =
                RetrofitHelper.getInstance().create(SearchApi::class.java)
                    .getMovies(
                        Constants.BASE_URL + movieName
                    )
            call.enqueue(object : Callback<Search?> {
                override fun onFailure(call: Call<Search?>, t: Throwable) {
                    Log.e("Amit", t.message, t)
                }

                override fun onResponse(
                    call: Call<Search?>,
                    response: Response<Search?>
                ) {
                    if (response.isSuccessful) {
                        Log.i("Amit", "Loaded.")
                        stringMutableLiveData.postValue(RequestResult.success(response.body()))
                    } else {
                        Log.e("Amit", "Error: ${response.code()} ${response.message()}")
                        stringMutableLiveData.postValue(RequestResult.error(response.message() + "\t" + response.code()))
                    }
                }
            })
        }
    }
}