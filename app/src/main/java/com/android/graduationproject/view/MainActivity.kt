package com.android.graduationproject.view

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebChromeClient.FileChooserParams.parseResult
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.MyAdapter
import com.android.graduationproject.data.ExamsResult
import com.android.graduationproject.data.ExamsResultsItem
import com.android.graduationproject.data.Student
import com.android.graduationproject.data.StudentIndex
import com.android.graduationproject.data.StudentIndexItem
import com.android.graduationproject.data.StudentInt
import com.android.graduationproject.databinding.ActivityMainBinding
import com.android.graduationproject.utils.APIConsumerImpl

import com.android.graduationproject.utils.RetrofitInstance
//import com.android.graduationproject.view_model.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val TAG: String =  "CHECK_RESPONSE"

   // lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        recyclerView = binding.rvExamresult
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        manager = LinearLayoutManager(this)
        //parseResult()
        getStudentData()
        //makeapicall()


    }
   private fun getStudentData(){
        val myNumber = intent.getStringExtra("Username").toString()
        val call:Call<Student> = RetrofitInstance.api.getAllResult(number = myNumber)
        call.enqueue(object :Callback<Student>{
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    val student: Student? = response.body()
                    student?.let {
                        displayStudentData(it)
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Log.e(TAG ,"onResponse")
            }
        })
   }

    private fun displayStudentData(student: Student) {
        val examList = student.examsResults
        myAdapter = MyAdapter((examList)) // Pass a list of one student for simplicity
        recyclerView.adapter = myAdapter
    }

}


