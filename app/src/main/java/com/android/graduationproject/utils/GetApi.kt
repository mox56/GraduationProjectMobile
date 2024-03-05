package com.android.graduationproject.utils

import com.android.graduationproject.data.StudentIndex
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetApi {

    @GET("studentdetail/{indexNumber}/")
    fun getExamResult(
        @Path("indexNumber") number: String
    ): Call<List<StudentIndex>>
}