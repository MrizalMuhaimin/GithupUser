package com.example.githupuser.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githupuser.data.db.dao.UserDao
import com.example.githupuser.data.db.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class FavoriteRoomDatabase: RoomDatabase() {
    abstract  fun favoriteDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteRoomDatabase::class.java, "favorite_database")
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }

}