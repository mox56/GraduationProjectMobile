package com.android.graduationproject.view_model



import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.graduationproject.Resource
import com.android.graduationproject.data.ExamResponse
import com.android.graduationproject.data.ExamResult
import com.android.graduationproject.repository.GetRepository
import com.android.graduationproject.utils.APIConsumer
import com.android.graduationproject.utils.APIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val getRepository: GetRepository): ViewModel() {

    val examresult: MutableLiveData<Resource<ExamResponse>> = MutableLiveData()






    fun getexamresult(number: String) = viewModelScope.launch {
        examresult.postValue(Resource.Loading())
        val response = getRepository.getExams(number)
        examresult.postValue(handleExamResponse(response))
    }

    private fun handleExamResponse(response: Response<ExamResponse>): Resource<ExamResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }



    var myResponse: MutableLiveData<Response<ExamResult>> = MutableLiveData()
    var myResponse2: MutableLiveData<Response<ExamResult>> = MutableLiveData()


    fun getExamresult2(number: String) {
        viewModelScope.launch {
            var response = getRepository.getExamResult2(number)
            myResponse2.value = response
        }

    }

}