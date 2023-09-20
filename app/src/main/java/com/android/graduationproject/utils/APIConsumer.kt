package com.android.graduationproject.utils

import com.android.graduationproject.data.AuthResponse
import com.android.graduationproject.data.LoginBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {

    @POST("/logintest")
    suspend fun loginUser(@Body body: LoginBody): Response<AuthResponse>

}
