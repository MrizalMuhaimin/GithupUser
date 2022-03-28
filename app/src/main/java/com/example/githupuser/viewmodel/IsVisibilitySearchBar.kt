package com.example.githupuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IsVisibilitySearchBar: ViewModel() {
    private var _isVisibility  = MutableLiveData<Boolean>()
    var isVisibility: LiveData<Boolean> = _isVisibility

    init {
        setTrue()
    }

    fun setTrue() {
        _isVisibility.value = true
    }

    fun setFalse(){
        _isVisibility.value = false
    }

}