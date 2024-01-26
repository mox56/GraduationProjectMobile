package com.android.graduationproject.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.graduationproject.data.BaseResponse
import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.data.LoginRequest
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.data.User
import com.android.graduationproject.repository.AuthRepository
import com.android.graduationproject.repository.UserRepository
import com.android.graduationproject.utils.APIConsumerImpl
import com.android.graduationproject.utils.AuthToken
import com.android.graduationproject.utils.RequestStatus
import kotlinx.coroutines.launch

class LoginActivityViewModel(application: Application) :
    ViewModel() {
        val userRepo = UserRepository()
        val loginResult: MutableLiveData<BaseResponse<LoginResponse>> =
            MutableLiveData()
    fun loginUser(username: String,password : String){
        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(
                    password = password,
                    username = username
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
            }
        }catch (ex:Exception) {
            loginResult.value = BaseResponse.Error(ex.message)
    }
        }
    }
}
