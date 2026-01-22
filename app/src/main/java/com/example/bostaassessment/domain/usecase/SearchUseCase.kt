package com.example.bostaassessment.domain.usecase

import com.example.bostaassessment.domain.model.Districts
import com.example.bostaassessment.domain.repository.SearchRepository
import com.example.bostaassessment.domain.util.AppError
import com.example.bostaassessment.domain.util.Result
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(): Result<Districts, AppError> {
        return repository.searchArea()
    }
}