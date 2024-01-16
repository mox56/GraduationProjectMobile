package com.android.graduationproject.utils

import com.android.graduationproject.data.AuthResponse
import com.android.graduationproject.data.ExamResponse
import com.android.graduationproject.data.ExamResult
import com.android.graduationproject.data.LoginBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIConsumer {

    @POST("/logintest")
    suspend fun loginUser(@Body body: LoginBody): Response<AuthResponse>

    @GET ("examresult/{indexNumber}/")
     fun getExamResult2(
        @Path("indexNumber") number: String
    ): Response<ExamResult>

     @GET("examresult/{indexNumber/")
     suspend fun getexamresult(
         @Path("indexNumber")number: String

     ): Response<ExamResponse>

}
