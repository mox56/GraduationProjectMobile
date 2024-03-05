package com.android.graduationproject.repository

import com.android.graduationproject.data.LoginRequest
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.utils.Api
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return Api.getApi()?.loginUser(loginRequest = loginRequest)
    }
}