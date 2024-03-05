package com.android.graduationproject.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.InvalidParameterException

class LoginActivityViewModelFactory(

    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CASt")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)) {
            return LoginActivityViewModel(application) as T
        }
        throw InvalidParameterException("Unable to construct LoginActivityModel")
    }
}