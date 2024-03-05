package com.android.graduationproject.utils

import com.android.graduationproject.data.LoginRequest
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.data.StudentIndex
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("/logintest")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    companion object {
        fun getApi(): Api? {
            return ApiClient.client?.create(Api::class.java)
        }
    }

    @GET("studentdetail/{indexNumber}/")
    fun getExamResult(
        @Path("indexNumber") number: String
    ): Call<List<StudentIndex>>
}