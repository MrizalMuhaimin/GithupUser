package com.example.githupuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleActivityModel: ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    fun updateActionBarTitle(title: String) = _title.postValue(title)
}