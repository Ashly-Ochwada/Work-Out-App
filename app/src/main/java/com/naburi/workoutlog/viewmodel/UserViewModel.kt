package com.naburi.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naburi.workoutlog.models.LogInRequest
import com.naburi.workoutlog.models.LogInResponse
import com.naburi.workoutlog.models.RegisterRequest
import com.naburi.workoutlog.models.RegisterResponse
import com.naburi.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LogInResponse>()
    val registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()
    val errorLiveData = MutableLiveData<String>()

    fun loginUser(logInRequest: LogInRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(logInRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }

    }
    }
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            } else {
                registerErrorLiveData.postValue(response.errorBody()?.string())
            }

        }
    }
}