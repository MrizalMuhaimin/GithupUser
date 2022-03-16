package com.example.githupuser.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val username:String,
    val name:String,
    val avatar:String,
    val company:String,
    val location: String,
    val repository:Int,
    val follower: Int,
    val following: Int
):Parcelable
