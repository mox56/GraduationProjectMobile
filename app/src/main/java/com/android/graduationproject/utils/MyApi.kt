package com.android.graduationproject.utils

import com.android.graduationproject.data.ExamsResult
import com.android.graduationproject.data.Student
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL = "http://172.20.10.2:8000/"

private val retrofit =
    Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

interface MyApi {
    @GET("studentdetail/{indexNumber}/")
    fun getAllResult(@Path("indexNumber") number: String):
            Call<Student>

    @PUT("exam_detail/{id}/")
    fun updateRequested(@Path("id") id: Int, @Body updatedResult: ExamsResult):
            Call<Response<Void>>
}

