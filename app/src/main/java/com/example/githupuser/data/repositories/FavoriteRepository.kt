package com.example.githupuser.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githupuser.data.db.FavoriteRoomDatabase
import com.example.githupuser.data.db.dao.UserDao
import com.example.githupuser.data.db.entities.UserEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mNotesDao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mNotesDao = db.favoriteDao()
    }

    fun getAllFavorite() = mNotesDao.getAllFavorite()

    fun getUserbyLogin(login:String) = mNotesDao.getUserbyLogin(login)

    fun insert(user: UserEntity) {
        executorService.execute { mNotesDao.insert(user) }
    }
    fun delete(user: UserEntity) {
        executorService.execute { mNotesDao.delete(user) }
    }
    fun update(user: UserEntity) {
        executorService.execute { mNotesDao.update(user) }
    }
}