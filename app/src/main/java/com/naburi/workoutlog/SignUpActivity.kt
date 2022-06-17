package com.naburi.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.naburi.workoutlog.databinding.ActivityHomeBinding
import com.naburi.workoutlog.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvHaveAnAccount.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
        binding.btnSignUp.setOnClickListener {
            validateSignUp() }

    }
    fun validateSignUp() {
        var error = false
        binding.tilLastName.error=null
        binding.tilFirstName.error = null
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
        var confirmPassword=binding.etConfirmPassword.text.toString()
        if (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error="Confirm Password is required"
        }
        if (password!=confirmPassword){
            binding.tilConfirmPassword.error = "Passwords do not match"
        }
        if(!error){

        }

    }
}