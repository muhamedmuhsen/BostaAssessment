package com.example.bostaassessment.data.mapper

import com.example.bostaassessment.data.dto.SearchResponse
import com.example.bostaassessment.domain.model.Districts

fun SearchResponse.toDistricts(): Districts{
    return Districts(
        `data` = data,
        message = message,
        success = success
    )
}