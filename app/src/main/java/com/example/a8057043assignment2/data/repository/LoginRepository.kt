package com.example.a8057043assignment2.data.repository
import com.example.a8057043assignment2.data.model.DashboardResponse
import com.example.a8057043assignment2.data.model.LoginRequest
import com.example.a8057043assignment2.data.model.LoginResponse
import com.example.a8057043assignment2.data.network.ApiService

class LoginRepository(private val apiService: ApiService) {

    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(LoginRequest(username, password))
    }

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return apiService.getDashboard(keypass)
    }
}
