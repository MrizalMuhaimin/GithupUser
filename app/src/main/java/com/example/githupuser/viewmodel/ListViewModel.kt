package com.example.githupuser.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.model.response.SearchResponse
import com.example.githupuser.data.network.api.githup.ApiConfigGithup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val _dataSearch = MutableLiveData<SearchResponse>()
    val dataSearch : LiveData<SearchResponse> = _dataSearch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        searchUser("rizal")
    }

    fun searchUser(keyword:String){
        _isLoading.value = true
        val client = ApiConfigGithup.getApiService().getSearchUser(keyword)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {

                    val responseBody = response.body()
                    if(responseBody != null){
                        _dataSearch.value = responseBody
                    }
                    _isLoading.value = false
                }else {
                    Log.e(TAG, "onFailure Search: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure Search: ${t.message.toString()}")
            }
        })
    }



}