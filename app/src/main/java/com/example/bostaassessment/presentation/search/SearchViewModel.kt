package com.example.bostaassessment.presentation.search

import com.example.bostaassessment.domain.util.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostaassessment.domain.usecase.SearchUseCase
import com.example.bostaassessment.presentation.utils.state.UiState
import com.example.bostaassessment.presentation.utils.strings.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onSearchQueryChange(searchQuery: String) {
        _uiState.update { _uiState.value.copy(searchQuery = searchQuery) }
        filterCitiesAndDistricts(_uiState.value.searchQuery)
    }

    private fun filterCitiesAndDistricts(searchQuery: String) {
        _uiState.update { state ->
            state.copy(
                filteredCities = state.cities.filter { city ->
                    city.cityName.contains(searchQuery, ignoreCase = true) ||

                            city.districts.any {
                        it.districtName.contains(
                            searchQuery,
                            ignoreCase = true
                        )
                    }
                })
        }
    }

    fun loadCitiesAndDistricts() {
        _uiState.update { _uiState.value.copy(searchState = UiState.Loading) }
        viewModelScope.launch {
            when (val result = searchUseCase()) {
                is Result.Success -> {
                    _uiState.update {
                        _uiState.value.copy(
                            searchState = UiState.Success(result.data),
                            cities = result.data.data,
                            filteredCities = result.data.data
                        )
                    }
                }
                is Result.Error -> {
                    _uiState.update {
                        _uiState.value.copy(searchState = UiState.Error(result.error.asUiText()))
                    }
                }
            }
        }
    }
}