package com.example.githupuser.ui.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githupuser.ui.insert.FavoriteAddUpdateViewModel
import com.example.githupuser.ui.main.FavoriteViewModel

class FavoriteModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteAddUpdateViewModel::class.java)) {
            return FavoriteAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: FavoriteModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): FavoriteModelFactory {
            if (INSTANCE == null) {
                synchronized(FavoriteModelFactory::class.java) {
                    INSTANCE = FavoriteModelFactory(application)
                }
            }
            return INSTANCE as FavoriteModelFactory
        }
    }
}