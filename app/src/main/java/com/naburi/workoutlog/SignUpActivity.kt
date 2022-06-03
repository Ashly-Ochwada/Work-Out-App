package com.naburi.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var etFirstName: TextInputEditText
    lateinit var tilFirstName: TextInputLayout
    lateinit var etLastName: TextInputEditText
    lateinit var tilLastName: TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var tilEmail: TextInputLayout
    lateinit var etConfirmPassword: TextInputEditText
    lateinit var tilConfirmPassword: TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var tilPassword: TextInputLayout
    lateinit var btnSignUp: Button
    lateinit var tvHaveAnAccount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFirstName = findViewById(R.id.etFirstName)
        tilFirstName = findViewById(R.id.tilFirstName)
        etLastName = findViewById(R.id.etLastName)
        tilLastName = findViewById(R.id.tilLastName)
        etEmail = findViewById(R.id.etEmail)
        tilEmail = findViewById(R.id.tilEmail)
        etPassword = findViewById(R.id.etPassword)
        tilPassword = findViewById(R.id.tilPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        tvHaveAnAccount = findViewById(R.id.tvHaveAnAccount)
        tvHaveAnAccount.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

            btnSignUp.setOnClickListener { validateSignUp() }
        }

    }
    fun validateSignUp() {
        var error = false
        tilLastName.error=null
        tilFirstName.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null
        var firstname = etFirstName.text.toString()
        if (firstname.isBlank()) {
            tilFirstName.error = "Firstname  is required"
        }
        var lastname=etLastName.text.toString()
        if (lastname.isBlank()){
            tilLastName.error="Lastname is required"
        }
        var email = etEmail.text.toString()
        if (email.isBlank()) {
            tilEmail.error = "Email is required"

        }
        var password = etPassword.text.toString()
        if (password.isBlank()) {
            tilPassword.error = "Password is required"

        }
        var confirmPassword=etConfirmPassword.text.toString()
        if (confirmPassword.isBlank()){
            tilConfirmPassword.error="Confirm Password is required"
        }

    }
}