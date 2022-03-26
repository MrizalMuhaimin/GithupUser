package com.example.githupuser.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.db.entities.UserEntity
import com.example.githupuser.data.repositories.FavoriteRepository


class FavoriteViewModel(application: Application):ViewModel() {
    private val mFavoriteRepository :FavoriteRepository = FavoriteRepository(application)

    fun getAllFavorite():LiveData<List<UserEntity>> = mFavoriteRepository.getAllFavorite()

    fun getUserByLogin(login:String):LiveData<List<UserEntity>> = mFavoriteRepository.getUserbyLogin(login)

//    fun isUserByLogin(login: String): LiveData<Boolean> {
//        val _isTrue = MutableLiveData<Boolean>()
//        var size : Int = 0
//        getUserByLogin(login).
//            size =  it.size
//
//        })
//        _isTrue.value = (size != 0)
//        return _isTrue
//
//    }
}