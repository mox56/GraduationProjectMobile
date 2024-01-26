package com.android.graduationproject.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.graduationproject.repository.GetRepository
import com.android.graduationproject.utils.APIConsumerImpl

/*class MainActivityViewModelFactory(
    private val apiConsumerImpl: APIConsumerImpl
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(apiConsumerImpl)as T
    }
}*/