package com.example.githupuser.data.network.api.githup

import com.example.githupuser.data.model.UserDetail
import com.example.githupuser.data.model.UserSearch
import com.example.githupuser.data.model.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceGithup {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") keyword: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<UserDetail>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ):Call<MutableList<UserSearch>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ):Call<MutableList<UserSearch>>



}