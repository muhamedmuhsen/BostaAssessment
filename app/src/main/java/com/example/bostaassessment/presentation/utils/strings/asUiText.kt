package com.example.bostaassessment.presentation.utils.strings

import com.example.bostaassessment.R
import com.example.bostaassessment.domain.util.AppError
import com.example.bostaassessment.domain.util.Result


fun AppError.asUiText(): UiText {
    return when (this) {
        AppError.Authentication.Unauthorized -> UiText.StringResource(R.string.error_unauthorized)
        AppError.Data.NOTFOUND -> UiText.StringResource(R.string.error_not_found)
        AppError.Network.BadRequest -> UiText.StringResource(R.string.error_bad_request)
        AppError.Network.NoInternetConnection -> UiText.StringResource(R.string.error_no_internet)
        AppError.Network.Timeout -> UiText.StringResource(R.string.error_timeout)
        AppError.Network.ServerError -> UiText.StringResource(R.string.error_server)
        AppError.Network.UnexpectedResponse -> UiText.StringResource(R.string.error_unexpected)
        AppError.Network.InvalidResponse -> UiText.StringResource(R.string.error_invalid_response)
        AppError.Network.TooManyRequests -> UiText.StringResource(R.string.error_too_many_requests)
    }
}

fun Result.Error<*, AppError>.asErrorUiText(): UiText {
    return error.asUiText()
}
