package com.example.githupuser.data.model.response

import android.os.Parcelable
import com.example.githupuser.data.model.UserSearch
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResponse(
    val total_count: Int?,
    val incomplete_result: Boolean?,
    val items: List<UserSearch>?,
):Parcelable
