package com.android.graduationproject.repository

import com.android.graduationproject.utils.APIService

class GetRepository {

    /*suspend fun getExamResult(): Response<ExamResult> {
        return APIService.api.getExamResult()
    }*/



    suspend fun getExams(number: String) = APIService.api.getexamresult(number)

}