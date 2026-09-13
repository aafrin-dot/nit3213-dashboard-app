package com.example.a8057043assignment2.ui.dashboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a8057043assignment2.data.model.Entity
import com.example.a8057043assignment2.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _entities = MutableStateFlow<List<Entity>>(emptyList())
    val entities: StateFlow<List<Entity>> = _entities

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDashboard(keypass)
                _entities.value = response.entities
            } catch (e: Exception) {
                _errorState.value = "Could not load data."
            }
        }
    }
}
