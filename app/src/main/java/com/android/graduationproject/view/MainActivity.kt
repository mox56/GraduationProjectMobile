package com.android.graduationproject.view

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.android.graduationproject.R
import com.android.graduationproject.TimeTableFragment
import com.android.graduationproject.databinding.ActivityMainBinding
import com.android.graduationproject.utils.APIConsumer
import com.android.graduationproject.utils.APIService

import retrofit2.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)}}



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


