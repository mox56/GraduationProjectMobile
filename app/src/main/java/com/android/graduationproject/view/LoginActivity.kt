package com.android.graduationproject.view


import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.graduationproject.R
import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.databinding.ActivityLoginBinding
import com.android.graduationproject.databinding.ActivityMainBinding
import com.android.graduationproject.repository.AuthRepository
import com.android.graduationproject.repository.GetRepository
import com.android.graduationproject.utils.APIService
import com.android.graduationproject.utils.VibrateView
import com.android.graduationproject.view_model.LoginActivityViewModel
import com.android.graduationproject.view_model.LoginActivityViewModelFactory
import com.android.graduationproject.view_model.MainViewModel
import com.android.graduationproject.view_model.MainViewModelFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    lateinit var btnSignin: Button
    lateinit var edtUsername: EditText

    private lateinit var binding: ActivityLoginBinding
    private lateinit var binding2: ActivityMainBinding
    private lateinit var ViewModel: LoginActivityViewModel
    private lateinit var ViewModel2: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        binding2 = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnSignin.setOnClickListener(this)
        binding.edtUsername.onFocusChangeListener = this
        binding.edtPassword.onFocusChangeListener = this
        binding.edtPassword.setOnKeyListener(this)

        btnSignin = findViewById(R.id.btn_signin)
        edtUsername = findViewById(R.id.edt_Username)

        var index = edtUsername.id



        val getRepository = GetRepository()
        val viewModelFactory = MainViewModelFactory(getRepository)
        ViewModel2 =ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        /*ViewModel2.getExamresult()*/
        ViewModel2.getExamresult2(index.toString())
        ViewModel = ViewModelProvider(
            this,
            LoginActivityViewModelFactory(AuthRepository(APIService.getService()), application)
        ).get(LoginActivityViewModel::class.java)

        setupObservers()
    }



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
        val intent = Intent(this@LoginActivity, MainViewModel::class.java)
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
}


