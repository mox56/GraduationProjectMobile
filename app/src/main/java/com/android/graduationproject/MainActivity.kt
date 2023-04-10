package com.android.graduationproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.graduationproject.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

import retrofit2.*
import java.io.IOException
import java.sql.Time


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.home ->replaceFragment(HomeFragment())
                R.id.ExamResults -> replaceFragment(ExamResultsFragment())
                R.id.TimeTable -> replaceFragment(TimeTableFragment())

            else ->{



                }
            }
            true



        }

        val recyclerViewer = findViewById<RecyclerView>(R.id.myRecyclerView)

        val serviceGenerator = ServiceGenerator.buildService(StudentAPI::class.java)
        val call = serviceGenerator.getStudents()



        call.enqueue(object : Callback<MutableList<StudentModel>> {
            override fun onResponse(
                call: Call<MutableList<StudentModel>>,
                response: Response<MutableList<StudentModel>>
            ) {
                if (response.isSuccessful) {
                    recyclerViewer.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = StudentAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<StudentModel>>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }

        })


    }
private fun replaceFragment(fragment: Fragment){
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frame_layout,fragment)
    fragmentTransaction.commit()
}
}



