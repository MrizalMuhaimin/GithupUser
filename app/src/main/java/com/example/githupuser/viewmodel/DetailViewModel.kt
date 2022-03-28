package com.example.githupuser.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.network.api.githup.ApiConfigGithup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _dataUsers = MutableLiveData<UserDetail>()
    val dataUsers: LiveData<UserDetail> = _dataUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setDetailUser(login: String){
        val client = ApiConfigGithup.getApiService().getUser(login)
        client.enqueue(object : Callback<UserDetail> {
            override fun onResponse(
                call: Call<UserDetail>,
                response: Response<UserDetail>
            ) {

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null){
                        _dataUsers.value = responseBody!!
                    }
                }else {
                    Log.e(ContentValues.TAG, "onFailure User 2: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure User: ${t.message.toString()}")
            }
        })
    }
}