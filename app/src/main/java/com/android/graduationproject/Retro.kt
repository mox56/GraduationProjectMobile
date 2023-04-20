package com.android.graduationproject

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit
    {
        val gson = GsonBuilder().setLenient().create()
        return  Retrofit.Builder()
            .baseUrl("http://192.168.0.7:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}