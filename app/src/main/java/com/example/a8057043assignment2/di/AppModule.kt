package com.example.a8057043assignment2.di
import com.example.a8057043assignment2.data.network.ApiService
import com.example.a8057043assignment2.data.network.RetrofitClient
import com.example.a8057043assignment2.data.repository.LoginRepository
import com.example.a8057043assignment2.ui.dashboard.DashboardViewModel
import com.example.a8057043assignment2.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ApiService> { RetrofitClient().apiService }

    single { LoginRepository(get()) }

    viewModel { LoginViewModel(get()) }

    viewModel { DashboardViewModel(get()) }
}
