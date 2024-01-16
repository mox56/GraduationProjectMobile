package com.android.graduationproject.view

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.graduationproject.data.ExamResponse
import com.android.graduationproject.databinding.ActivityMainBinding
import com.android.graduationproject.repository.GetRepository
import com.android.graduationproject.utils.APIService
import com.android.graduationproject.view_model.MainViewModel
import com.android.graduationproject.view_model.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

import retrofit2.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = MainViewModel(getRepository= GetRepository())
        setContentView(binding.root)

        var examList = mutableListOf<>()





        val myNumber = intent.getStringExtra("Username")
        val getRepository = GetRepository()
        val viewModelFactory = MainViewModelFactory(getRepository)


        val examresult = viewModel.getExamresult2(myNumber.toString())
        viewModel =ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getExamresult2(myNumber.toString())
        viewModel.myResponse2.observe(this, Observer { response->
            if(response.isSuccessful){




            }else{


            }
        })




}


  /*  private fun getExamResults(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIConsumer::class.java)

        api.getExamResult().enqueue(object : Callback<List<ExamResult>>{
            override fun onResponse(
                call: Call<List<ExamResult>>,
                response: Response<List<ExamResult>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        for (exam in it){
                            Log.i(TAG, "onResponse: ${exam.Mark}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ExamResult>>, t: Throwable) {
                Log.i(TAG, "onFailure ${t.message}")
            }

        })
    }

}*/



        /*binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {


                R.id.TimeTable -> replaceFragment(TimeTableFragment())

                else -> {


                }
            }
            true


        }

        val ScrollView = findViewById<NestedScrollView>(R.id.scrollView)*/




        /*call.enqueue(object : Callback<MutableList<StudentModel>> {
            override fun onResponse(
                call: Call<MutableList<StudentModel>>,
                response: Response<MutableList<StudentModel>>
            ) {
                if (response.isSuccessful) {
                    ScrollView.apply {
                        layoutManager = NestedScrollView(this@MainActivity)
                        adapter = StudentAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<StudentModel>>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }

        })


    }*/

    /*private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.scrollView, fragment)
        fragmentTransaction.commit()
    }
}
*/


