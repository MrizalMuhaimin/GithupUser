package com.example.githupuser.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.db.entities.UserEntity

class FavoriteViewModel(application: Application):ViewModel() {
    private val mFavoriteViewModel :FavoriteViewModel = FavoriteViewModel(application)

    fun getAllFavorite():LiveData<List<UserEntity>> = mFavoriteViewModel.getAllFavorite()

    fun getUserByLogin(login:String):LiveData<List<UserEntity>> = mFavoriteViewModel.getUserByLogin(login)
}