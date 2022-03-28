package com.example.githupuser.ui.main

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.db.entities.UserEntity
import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.repositories.FavoriteRepository


class FavoriteViewModel(application: Application):ViewModel() {
    private val mFavoriteRepository :FavoriteRepository = FavoriteRepository(application)
    private val _dataUsers = MutableLiveData<List<UserEntity>>()
    val dataUsers: LiveData<List<UserEntity>> = _dataUsers

    fun getAllFavorite():LiveData<List<UserEntity>> = mFavoriteRepository.getAllFavorite()

    fun getUserByLogin(login: String):LiveData<List<UserEntity>> = mFavoriteRepository.getUserbyLogin(login)





}