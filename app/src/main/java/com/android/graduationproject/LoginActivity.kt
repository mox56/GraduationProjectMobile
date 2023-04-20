package com.android.graduationproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.android.graduationproject.databinding.ActivityLoginBinding


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initAction()
    }

    fun initAction(){
            binding.btnSignin.setOnClickListener{
            login()
        }
    }

    fun login(){
        val request = UserRequest()
        request.username=binding.edtUsername.text.toString().trim()
        request.password=binding.edtPassword.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message!!)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                Log.e("token", user!!.data?.token!!)
                Log.e("username", user!!.data?.username!!)
            }
        })


        }
}