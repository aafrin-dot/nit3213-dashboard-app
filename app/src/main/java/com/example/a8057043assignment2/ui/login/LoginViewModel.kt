package com.example.a8057043assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8057043assignment2.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _keypass = MutableStateFlow<String?>(null)
    val keypass: StateFlow<String?> = _keypass

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun login(username: String, password: String) {
        _errorState.value = null
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                _keypass.value = response.keypass
            } catch (e: Exception) {
                _errorState.value = "Login failed. Check your details."
            }
        }
    }
}