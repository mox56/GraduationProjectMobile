package com.android.graduationproject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {
    @Headers("Content-Type:application/json")
    @POST("login")
    fun login(@Body userRequest: UserRequest): Call<UserResponse>

    @Headers("Content-Type:application/json")
    @POST("users")
    fun registerUser(
        @Body info: TimeTableFragment
    ): retrofit2.Call<ResponseBody>

    @GET("/students")
    fun getStudents (): Call<MutableList<StudentModel>>

}
