package com.example.githupuser.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githupuser.data.util.USER_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = USER_TABLE_NAME)
@Parcelize
data class UserEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val login: String?,
    val node_id: String?,
    val avatar_url: String?,
    val gravatar_id: String?,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organization_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: String?,
    val bio: String?,
    val twitter_username: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int?,
    val following: Int?,
    val created_at: String?,
    val updated_at: String?,
):Parcelable