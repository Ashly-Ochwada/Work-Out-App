package com.naburi.workoutlog.repository

import com.naburi.workoutlog.api.ApiClient
import com.naburi.workoutlog.api.ApiInterface
import com.naburi.workoutlog.models.LogInRequest
import com.naburi.workoutlog.models.LogInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser (loginRequest: LogInRequest):Response<LogInResponse>
            = withContext(Dispatchers.IO){
        val response=apiClient.loginUser(loginRequest)
        return@withContext response
    }
}