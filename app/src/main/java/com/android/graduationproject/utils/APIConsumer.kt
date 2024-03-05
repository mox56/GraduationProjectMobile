package com.android.graduationproject.utils

import com.android.graduationproject.data.AuthResponse
import com.android.graduationproject.data.ExamResponse
import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.data.StudentIndex
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIConsumer {

    @POST("/logintest")
    suspend fun loginUser(
        @Body body: LoginBody
    ): Response<AuthResponse>

    @GET("studentdetail/{indexNumber}/")
    fun getExamResult(
        @Path("indexNumber") number: String
    ): Call<List<StudentIndex>>

    @GET("studentdetail/{indexNumber/")
    suspend fun getexamresult(
        @Path("indexNumber") number: String

    ): Response<ExamResponse>

    @GET("studentdetail/{indexNumber/")
    suspend fun getexamlist(
        @Path("indexNumber") number: String

    ): Call<List<ExamResponse>>


}
