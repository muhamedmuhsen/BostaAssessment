package com.example.bostaassessment.presentation.search

import android.util.Log
import com.example.bostaassessment.domain.util.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostaassessment.domain.usecase.SearchUseCase
import com.example.bostaassessment.presentation.utils.state.UiState
import com.example.bostaassessment.presentation.utils.strings.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
        onSearchClicked()
    }
    fun onSearchQueryChange(searchQuery: String){
        _uiState.value = _uiState.value.copy(searchQuery = searchQuery)
    }
    fun onSearchClicked() {
        _uiState.value = _uiState.value.copy(searchState = UiState.Loading)
        viewModelScope.launch {
            when (val result = searchUseCase()) {
                is Result.Success -> {
                    Log.d("SearchViewModel", "onSearchClicked: ${result.data}")
                    _uiState.value = _uiState.value.copy(
                        searchState = UiState.Success(result.data),
                        cities = result.data.data
                    )
                }
                is Result.Error -> {
                    Log.d("SearchViewModel", "onSearchClicked: ${result.error}")
                    _uiState.value = _uiState.value.copy(searchState = UiState.Error(result.error.asUiText()),)
                }
            }
        }
    }

}