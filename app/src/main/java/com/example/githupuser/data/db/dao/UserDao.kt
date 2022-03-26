package com.example.githupuser.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githupuser.data.db.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: UserEntity)

    @Update
    fun update(note: UserEntity)

    @Delete
    fun delete(note: UserEntity)

    @Query("SELECT * from UserEntity ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<UserEntity>>

    @Query("SELECT * from UserEntity WHERE login = :login")
    fun getUserbyLogin(login:String): LiveData<List<UserEntity>>
}