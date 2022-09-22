package com.naburi.workoutlog.api

import com.naburi.workoutlog.models.LogInRequest
import com.naburi.workoutlog.models.LogInResponse
import com.naburi.workoutlog.models.RegisterRequest
import com.naburi.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body logInRequest: LogInRequest): Response<LogInResponse>
}