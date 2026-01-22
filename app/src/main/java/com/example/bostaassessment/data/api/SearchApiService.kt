package com.example.bostaassessment.data.api

import com.example.bostaassessment.data.dto.SearchResponse
import retrofit2.http.GET


interface SearchApiService {
    @GET("cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5")
    suspend fun searchArea(): SearchResponse
}