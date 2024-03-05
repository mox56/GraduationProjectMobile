package com.android.graduationproject.view


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.graduationproject.data.BaseResponse
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.data.SessionManager
import com.android.graduationproject.databinding.ActivityLoginBinding
import com.android.graduationproject.view_model.LoginActivityViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginActivityViewModel
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        viewModel = LoginActivityViewModel(application)
        setContentView(binding.root)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()

                }

                is BaseResponse.Error -> {
                    processError(it.msg)

                }

                else -> {
                    stopLoading()
                }
            }
        }
        binding.btnSignin.setOnClickListener {
            doLogin()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, ExamActivity::class.java)

        startActivity(intent)
    }

    fun doLogin() {
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()
        intent = Intent(this@LoginActivity, ExamActivity::class.java)
        intent.putExtra("Username", username)
        viewModel.loginUser(username = username, password = password)
        startActivity(intent)
    }


    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.user)

        navigateToHome()
    }


    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}




