package com.android.graduationproject.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.MyAdapter
import com.android.graduationproject.data.Student
import com.android.graduationproject.databinding.ActivityExamBinding
import com.android.graduationproject.utils.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExamActivity : AppCompatActivity(), MyAdapter.OnButtonClickListener {

    private val TAG: String = "CHECK_RESPONSE"


    private lateinit var binding: ActivityExamBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: MyAdapter
    private lateinit var welcomeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        recyclerView = binding.rvExamresult
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        welcomeTextView = binding.tvWelcome

        manager = LinearLayoutManager(this)
        getStudentData()


        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun getStudentData() {
        val myNumber = intent.getStringExtra("Username").toString()
        val call: Call<Student> = RetrofitInstance.api.getAllResult(number = myNumber)
        call.enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    val student: Student? = response.body()
                    student?.let {
                        displayStudentData(it)
                        welcomeTextView.text = "Welcome, ${student.name}!"
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Log.e(TAG, "onResponse")
            }
        })
    }

    private fun displayStudentData(student: Student) {
        val examList = student.examsResults
        myAdapter = MyAdapter(examList, this)
        recyclerView.adapter = myAdapter
    }

    override fun onButtonClick(position: Int) {
        myAdapter.sendPutRequest(position)
        fun showToast(msg: String) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
        showToast("Course Requested Successful")

    }

    private fun logout() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


}





