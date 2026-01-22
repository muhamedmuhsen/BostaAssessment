package com.example.bostaassessment.domain.util

sealed interface AppError : Error {
    enum class Network : AppError {
        BadRequest,
        NoInternetConnection,
        Timeout,
        ServerError,
        UnexpectedResponse,
        InvalidResponse,
        TooManyRequests,
    }

    enum class Authentication : AppError{
        Unauthorized
    }

    enum class Data : AppError{
        NOTFOUND
    }
}