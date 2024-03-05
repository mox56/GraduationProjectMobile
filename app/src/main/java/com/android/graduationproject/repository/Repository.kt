package com.android.graduationproject.repository

import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.utils.RequestStatus
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Repository {

    @POST("/logintest")
    suspend fun loginUser(
        @Body body: LoginBody
    ): Flow<RequestStatus<LoginResponse>>

    @GET("studentdetail/{indexNumber}/")
    fun getExamResult(
        @Path("indexNumber") number: String
    )
}