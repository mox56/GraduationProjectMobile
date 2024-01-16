package com.android.graduationproject.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.graduationproject.repository.GetRepository

class MainViewModelFactory(
    private val getRepository: GetRepository
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getRepository)as T
    }
}