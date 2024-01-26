package com.android.graduationproject.view


import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.graduationproject.R
import com.android.graduationproject.data.AuthResponse
import com.android.graduationproject.data.BaseResponse
import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.data.LoginResponse
import com.android.graduationproject.data.SessionManager
import com.android.graduationproject.databinding.ActivityLoginBinding
import com.android.graduationproject.databinding.ActivityMainBinding
import com.android.graduationproject.repository.AuthRepository
import com.android.graduationproject.repository.GetRepository
import com.android.graduationproject.utils.APIConsumer
import com.android.graduationproject.utils.APIConsumerImpl
import com.android.graduationproject.utils.APIService
import com.android.graduationproject.utils.VibrateView
import com.android.graduationproject.view_model.LoginActivityViewModel
import com.android.graduationproject.view_model.LoginActivityViewModelFactory
//import com.android.graduationproject.view_model.MainActivityViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var btnSignin: Button
    lateinit var edtUsername: EditText
    lateinit var  viewModel: LoginActivityViewModel
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        viewModel  = LoginActivityViewModel(application )
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
                    //processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }

                else -> {
                    stopLoading()
                }
            }
        }
        binding.btnSignin.setOnClickListener{
            doLogin()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
       // intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    fun doLogin() {
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()
        intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("Username",username)
        viewModel.loginUser(username = username, password = password)
        startActivity(intent)
    }

    fun mainActivity() {
    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.user)
       // if (!data?.token?.isNullOrEmpty()!!) {
         //   data?.token?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }


    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}




    /*    binding.btnSignin.setOnClickListener(this)
        binding.edtUsername.onFocusChangeListener = this
        binding.edtPassword.onFocusChangeListener = this
        binding.edtPassword.setOnKeyListener(this)
        btnSignin = findViewById(R.id.btn_signin)
        edtUsername = findViewById(R.id.edt_Username)

    }


    view

    private fun validateUsername(
        shouldUpdateView: Boolean = true,
        shouldVibrateView: Boolean = true
    ): Boolean {

        var errorMessage: String? = null
        val value = binding.edtUsername.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Username is required"
        }
        if (errorMessage != null && shouldUpdateView) {
            binding.usernametil.apply {
                isErrorEnabled = true
                error = errorMessage
                if (shouldVibrateView) VibrateView.vibrate(this@LoginActivity, this)
            }
        }
        return errorMessage == null
    }


    private fun validatePassword(
        shouldUpdateView: Boolean = true,
        shouldVibrateView: Boolean = true
    ): Boolean {

        var errorMessage: String? = null
        val value = binding.edtPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = "Password must be 6 characters long"
        }
        if (errorMessage != null && shouldUpdateView) {
            binding.passwordtil.apply {
                isErrorEnabled = true
                error = errorMessage
                if (shouldVibrateView) VibrateView.vibrate(this@LoginActivity, this)

            }
        }
        return errorMessage == null
    }

    private fun validate(): Boolean {
        var isValid = true
        if (!validateUsername(shouldVibrateView = false)) isValid = false
        if (!validatePassword(shouldVibrateView = false)) isValid = false
        if (!isValid) VibrateView.vibrate(this, binding.cardView)

        return isValid
    }

    private fun setupObservers() {
        ViewModel.getIsLoading().observe(this) {
            binding.progressBar.isVisible = it
        }

        ViewModel.getErrorMessage().observe(this) {
            val formErrorKeys = arrayOf("username", "password")
            val message = StringBuilder()
            it.map { entry ->
                if (formErrorKeys.contains(entry.key)) {
                    when (entry.key) {
                        "username" -> {
                            binding.usernametil.apply {
                                isErrorEnabled = true
                                error = entry.value
                            }
                        }

                        "password" -> {
                            binding.passwordtil.apply {
                                isErrorEnabled = true
                                error = entry.value
                            }
                        }
                    }
                } else {
                    message.append(entry.value).append("\n")
                }

                if (message.isNotEmpty()) {
                    AlertDialog.Builder(this)
                        .setTitle("INFORMATION")
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ -> dialog!!.dismiss() }
                        .show()
                }
            }
        }
        ViewModel.getUser().observe(this) {
            if (it != null) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }


    private fun submitForm() {
        if (validate()) {
            ViewModel.loginUser(
                LoginBody(
                    binding.edtUsername.text!!.toString(),
                    binding.edtPassword.text!!.toString()
                )
            )
        }


    }

    override fun onClick(view: View?) {
        val edtusername = edtUsername.text.toString()
        intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("Username",edtusername)
        if (view != null) {
            when (view.id) {
                R.id.btn_signin -> {
                    submitForm()
                    startActivity(intent)
                }
            }
        }
     /*   val studentIndex = R.id.edt_Username
        var Course1 =binding2.course1
        var Mark1 = binding2.semester1
        ViewModel2.getExamresult2((studentIndex).toString())
        ViewModel2.myResponse2.observe(this, Observer { response->
            if (response.isSuccessful){
                Course1= response.body()!!.Course_code
            }
            
        })*/
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.edt_Username -> {
                    if (hasFocus) {
                        if (binding.usernametil.isErrorEnabled) {
                            binding.usernametil.isErrorEnabled = false
                        }
                    } else {
                        validateUsername()
                    }
                }

                R.id.edt_Password -> {
                    if (hasFocus) {
                        if (binding.passwordtil.isErrorEnabled) {
                            binding.passwordtil.isErrorEnabled = false
                        }
                    } else {
                        validatePassword()
                    }
                }
            }
        }
    }



    override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
        val edtusername = edtUsername.text.toString()
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("Username",edtusername)

        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent!!.action == KeyEvent.ACTION_UP) {
            submitForm()
            startActivity(intent)
        }
        return false

    }
}*/


