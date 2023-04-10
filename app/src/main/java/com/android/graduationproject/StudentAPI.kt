package com.android.graduationproject

import retrofit2.Call
import retrofit2.http.GET

interface StudentAPI {
    @GET("/students")
    fun getStudents (): Call<MutableList<StudentModel>>
}