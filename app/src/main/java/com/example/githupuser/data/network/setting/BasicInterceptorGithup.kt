package com.example.githupuser.data.network.setting

import okhttp3.Interceptor
import okhttp3.Response

class BasicInterceptorGithup(private var token: String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val basicReq = req.newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(basicReq)
    }


}