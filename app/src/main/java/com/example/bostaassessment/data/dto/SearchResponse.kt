package com.example.bostaassessment.data.dto

import com.example.bostaassessment.domain.model.Data

data class SearchResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)
