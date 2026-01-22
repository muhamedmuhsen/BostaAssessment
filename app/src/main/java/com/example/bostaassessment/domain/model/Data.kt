package com.example.bostaassessment.domain.model

data class Data(
    val cityCode: String,
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val districts: List<District>,
    val dropOffAvailability: Boolean,
    val pickupAvailability: Boolean
)