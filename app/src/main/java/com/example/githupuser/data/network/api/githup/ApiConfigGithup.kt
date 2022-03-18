package com.example.githupuser.data.network.api.githup

import com.example.githupuser.BuildConfig
import com.example.githupuser.data.network.setting.BasicInterceptorGithup
import com.example.githupuser.data.util.ACCES_TOKEN
import com.example.githupuser.data.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfigGithup {
    companion object{
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        fun getApiService():ApiServiceGithup {
            val client = OkHttpClient.Builder()
                .addInterceptor(BasicInterceptorGithup(ACCES_TOKEN))
                .addInterceptor(logger)
                .connectTimeout(30,TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiServiceGithup::class.java)
        }


    }
}