package com.naburi.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.naburi.workoutlog.api.ApiClient
import com.naburi.workoutlog.api.ApiInterface
import com.naburi.workoutlog.databinding.ActivitySignUpBinding
import com.naburi.workoutlog.models.RegisterRequest
import com.naburi.workoutlog.models.RegisterResponse
import com.naburi.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvHaveAnAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.btnSignUp.setOnClickListener {
            validateSignUp() }

    }
    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveData.observe(this, androidx.lifecycle.Observer { registerResponse ->
            Toast.makeText(baseContext, registerResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, LoginActivity::class.java))
        })
        userViewModel.registerErrorLiveData.observe(this,
            androidx.lifecycle.Observer { registerError ->
            Toast.makeText(baseContext, registerError, Toast.LENGTH_LONG).show()
        })
    }

    fun validateSignUp() {
        var error = false
        binding.tilLastName.error=null
        binding.tilFirstName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null

        var firstname = binding.etFirstName.text.toString()
        if (firstname.isBlank()) {
            binding.tilFirstName.error = "Firstname  is required"
        }
        var lastname=binding.etLastName.text.toString()
        if (lastname.isBlank()){
            binding.tilLastName.error="Lastname is required"
        }
        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"

        }
        var password = binding.etPassword.text.toString()
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"

        }
        var phonenumber = binding.etPhoneNumber.text.toString()
        if (phonenumber.isBlank()) {
            binding.tilPhoneNumber.error = "Phone Number is required"

        }
        var confirmPassword=binding.etConfirmPassword.text.toString()
        if (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error="Confirm Password is required"
        }
        if (password!=confirmPassword){
            binding.tilConfirmPassword.error = "Passwords do not match"
        }
        if(!error){
            val registerRequest = RegisterRequest(firstname, lastname,phonenumber,email,password )
//            startActivity(Intent(this,LoginActivity::class.java))
            userViewModel.registerUser(registerRequest)

        }

    }

    }



