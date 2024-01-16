package com.android.graduationproject.utils

import android.content.Context
import android.widget.Toast
import com.android.graduationproject.data.ExamResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import retrofit2.http.Path

object APIService {
    const val BASE_URL = "http://172.20.10.2:8000/"


    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }



    val api: APIConsumer by lazy {
        retrofit.create(APIConsumer::class.java)
    }
    fun getService(): APIConsumer {
        val client: OkHttpClient = OkHttpClient.Builder()
            .build()

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit: Retrofit = builder.build()
        return retrofit.create(APIConsumer::class.java)
    }


    fun getApiService():APIConsumer{
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        // Client
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(APIConsumer::class.java)
    }


}


    /* fun getExamResult2(context: Context, callback: (ExamResult) -> Unit) {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APIConsumer = retrofit.create<APIConsumer>(APIConsumer::class.java)
        val response: Response<ExamResult> = service.getExamResult2(("indexNumber"))
        response.enqueue(object : Response<ExamResult> {
            override fun onResponse(call: Call<ExamResult>, response: Response<ExamResult>) {
                if (response!!.isSuccessful) {
                    val examresult: ExamResult = response.body() as ExamResult
                    callback(examresult)
                }

            }

            override fun onFailure(call: Call<ExamResult>, t: Throwable) {
                // This method is called when the API request fails.
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
