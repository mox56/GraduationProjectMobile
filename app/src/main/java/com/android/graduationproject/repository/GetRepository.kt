package com.android.graduationproject.repository

import com.android.graduationproject.data.ExamResult
import com.android.graduationproject.utils.APIService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class GetRepository {

    /*suspend fun getExamResult(): Response<ExamResult> {
        return APIService.api.getExamResult()
    }*/

    suspend fun getExamResult2(number: String): Response<ExamResult> {
        return APIService.api.getExamResult2(number)
    }

    suspend fun getExams(number: String) = APIService.api.getexamresult(number)

}