package com.example.githupuser.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.data.model.response.SearchResponse
import com.example.githupuser.data.network.api.githup.ApiConfigGithup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    private val _dataFollow = MutableLiveData<MutableList<UserSearch>>()
    val dataFollow: LiveData<MutableList<UserSearch>> = _dataFollow

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setDataFollower(login: String){
        val client = ApiConfigGithup.getApiService().getFollowers(login)
        client.enqueue(object : Callback<MutableList<UserSearch>> {
            override fun onResponse(
                call: Call<MutableList<UserSearch>>,
                response: Response<MutableList<UserSearch>>
            ) {

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null){
                        _dataFollow.value = responseBody
                    }
                }else {
                    Log.e(ContentValues.TAG, "onFailure User 2: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<MutableList<UserSearch>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure User: ${t.message.toString()}")
            }
        })
    }

    fun setDataFollowering(login: String){
        val client = ApiConfigGithup.getApiService().getFollowing(login)
        client.enqueue(object : Callback<MutableList<UserSearch>> {
            override fun onResponse(
                call: Call<MutableList<UserSearch>>,
                response: Response<MutableList<UserSearch>>
            ) {

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null){
                        _dataFollow.value = responseBody
                    }
                }else {
                    Log.e(ContentValues.TAG, "onFailure User 2: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<MutableList<UserSearch>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure User: ${t.message.toString()}")
            }
        })
    }
}