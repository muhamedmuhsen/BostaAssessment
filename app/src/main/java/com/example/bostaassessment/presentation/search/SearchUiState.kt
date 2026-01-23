package com.example.bostaassessment.presentation.search

import com.example.bostaassessment.domain.model.Data
import com.example.bostaassessment.domain.model.Districts
import com.example.bostaassessment.presentation.utils.state.UiState

data class SearchUiState(
    val searchQuery: String = "",
    val cities: List<Data> = emptyList(),
    val filteredCities: List<Data> = emptyList(),
    val isCityClicked: Boolean = false,
    val selectedCityId: List<String> = emptyList(),
    val searchState: UiState<Districts> = UiState.Idle
)
