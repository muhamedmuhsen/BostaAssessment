package com.example.bostaassessment.domain.repository

import com.example.bostaassessment.domain.model.Districts
import com.example.bostaassessment.domain.util.AppError
import com.example.bostaassessment.domain.util.Result

interface SearchRepository {
    suspend fun searchArea(): Result<Districts, AppError>
}