package com.android.graduationproject.repository

import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.utils.APIConsumer
import com.android.graduationproject.utils.RequestStatus
import com.android.graduationproject.utils.SimplifiedMessage
import kotlinx.coroutines.flow.flow


class AuthRepository(val consumer: APIConsumer) {

    fun loginUser(body: LoginBody) = flow {
        emit(RequestStatus.Waiting)
        val response = consumer.loginUser(body)
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
}