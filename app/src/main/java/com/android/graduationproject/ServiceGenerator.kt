package com.android.graduationproject

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
     val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://172.20.10.8:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}