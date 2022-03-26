package com.example.githupuser.ui.insert

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githupuser.data.db.entities.UserEntity
import com.example.githupuser.data.repositories.FavoriteRepository

class FavoriteAddUpdateViewModel (application: Application):ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(user : UserEntity){
        mFavoriteRepository.insert(user)
    }

    fun delete(user: UserEntity){
        mFavoriteRepository.delete(user)
    }

    fun update(user: UserEntity){
        mFavoriteRepository.update(user)
    }

}