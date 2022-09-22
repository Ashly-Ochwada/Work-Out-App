package com.naburi.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.naburi.workoutlog.api.ApiClient
import com.naburi.workoutlog.api.ApiInterface
import com.naburi.workoutlog.databinding.ActivityLoginBinding
import com.naburi.workoutlog.models.LogInRequest
import com.naburi.workoutlog.models.LogInResponse
import com.naburi.workoutlog.models.RegisterResponse
import com.naburi.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)


        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            validateLogin()

        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { LogInResponse->
            Toast.makeText(baseContext, LogInResponse?.message, Toast.LENGTH_LONG).show()
            saveLoginDetails(  LogInResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))

        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(baseContext, errorMessage, Toast.LENGTH_LONG).show()

        })
    }

    fun validateLogin() {
        var error = false
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.error = "Not a valid email address"
            error = true
        }
        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error) {
            val logInRequest = LogInRequest(email, password)
            binding.pbLogIn.visibility = View.VISIBLE
            userViewModel.loginUser(logInRequest)


        }
    }

    fun saveLoginDetails(logInResponse: LogInResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", logInResponse.accessToken)
        editor.putString("USER_ID", logInResponse.userId)
        editor.putString("PROFILE_ID", logInResponse.profileId)
        editor.apply()
    }

}