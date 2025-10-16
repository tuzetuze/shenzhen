package com.shenzhen.housing.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shenzhen.housing.data.model.HousingPolicy
import com.shenzhen.housing.data.model.HousingProject
import com.shenzhen.housing.data.model.ApplicationGuide
import com.shenzhen.housing.data.repository.HousingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HousingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // 这里可以加载初始数据
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "未知错误"
                )
            }
        }
    }

    fun searchPolicies(query: String) {
        viewModelScope.launch {
            try {
                repository.searchPolicies(query).collect { policies ->
                    _uiState.value = _uiState.value.copy(
                        searchResults = policies,
                        isSearching = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "搜索失败",
                    isSearching = false
                )
            }
        }
    }

    fun searchProjects(query: String) {
        viewModelScope.launch {
            try {
                repository.searchProjects(query).collect { projects ->
                    _uiState.value = _uiState.value.copy(
                        projectSearchResults = projects,
                        isSearching = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "搜索失败",
                    isSearching = false
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class MainUiState(
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val searchResults: List<HousingPolicy> = emptyList(),
    val projectSearchResults: List<HousingProject> = emptyList(),
    val error: String? = null
)
