package com.android.graduationproject.view


import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.android.graduationproject.R
import com.android.graduationproject.data.LoginBody
import com.android.graduationproject.databinding.ActivityLoginBinding
import com.android.graduationproject.repository.AuthRepository
import com.android.graduationproject.utils.APIService
import com.android.graduationproject.utils.VibrateView
import com.android.graduationproject.view_model.LoginActivityViewModel
import com.android.graduationproject.view_model.LoginActivityViewModelFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var ViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.btnSignin.setOnClickListener(this)
        binding.edtUsername.onFocusChangeListener = this
        binding.edtPassword.onFocusChangeListener = this
        binding.edtPassword.setOnKeyListener(this)


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

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.btn_signin -> {
                    submitForm()
                }
            }
        }
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

    override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent!!.action == KeyEvent.ACTION_UP) {
            submitForm()
        }
        return false

    }
}


