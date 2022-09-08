package com.naburi.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.naburi.workoutlog.api.ApiClient
import com.naburi.workoutlog.api.ApiInterface
import com.naburi.workoutlog.databinding.ActivityLoginBinding
import com.naburi.workoutlog.models.LogInRequest
import com.naburi.workoutlog.models.LogInResponse
import com.naburi.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences

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
            makeLoginRequest(logInRequest)


        }
    }
    fun makeLoginRequest(logInRequest: LogInRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.loginUser(logInRequest)

        request.enqueue(object : Callback<LogInResponse> {
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                binding.pbLogIn.visibility = View.GONE
                if (response.isSuccessful) {
                    val logInResponse = response.body()
                    Toast.makeText(baseContext, logInResponse?.message, Toast.LENGTH_LONG).show()
                    saveLoginDetails(logInResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                } else {
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }


            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                binding.pbLogIn.visibility = View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })

    }
    fun saveLoginDetails(logInResponse: LogInResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN", logInResponse.accessToken)
        editor.putString("USER_ID", logInResponse.userId)
        editor.putString("PROFILE_ID", logInResponse.profileId)
        editor.apply()
    }

}