package com.naburi.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.naburi.workoutlog.api.ApiClient
import com.naburi.workoutlog.api.ApiInterface
import com.naburi.workoutlog.databinding.ActivitySignUpBinding
import com.naburi.workoutlog.models.RegisterRequest
import com.naburi.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
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
            makeRegisterRequest(registerRequest)
        }

    }
    fun makeRegisterRequest(registerRequest: RegisterRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val  request = apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse( call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    //Navigate to login
                }
                else{
                    val error =response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}


