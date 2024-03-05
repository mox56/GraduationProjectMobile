package com.android.graduationproject.utils

import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.data.LoginRequest
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class APIConsumerImpl : Repository {

    private val BASE_URL = "http://172.20.10.2:8000/"

    private val api: Api


    init {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
        api = retrofit.create(Api::class.java)
    }

    suspend fun loginUser(body: LoginRequest) = flow {
        emit(RequestStatus.Waiting)
        val response = api.loginUser(body)
        if (response.isSuccessful) {
            emit((RequestStatus.Success(response.body()!!)))
        } else {
            emit(
                RequestStatus.Error(
                    SimplifiedMessage.get(
                        response.errorBody()!!.byteStream().reader().readText()
                    )
                )

            )

        }

    }

    override suspend fun loginUser(body: LoginBody): Flow<RequestStatus<LoginResponse>> {
        return loginUser(body)
    }


    override fun getExamResult(number: String) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }


}