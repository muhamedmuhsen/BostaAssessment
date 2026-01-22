package com.example.bostaassessment.data.repository

import com.example.bostaassessment.data.api.SearchApiService
import com.example.bostaassessment.data.mapper.toDistricts
import com.example.bostaassessment.domain.model.Districts
import com.example.bostaassessment.domain.repository.SearchRepository
import com.example.bostaassessment.domain.util.AppError
import com.example.bostaassessment.domain.util.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.let

class SearchRepositoryImpl @Inject constructor(private val apiService: SearchApiService) :
    SearchRepository {
    override suspend fun searchArea(): Result<Districts, AppError> {
        return try {
            val response = apiService.searchArea()
            response.takeIf { it.success }
                ?.let { Result.Success(it.toDistricts()) }
                ?: Result.Error(AppError.Network.InvalidResponse)

        } catch (_: UnknownHostException) {
            Result.Error(AppError.Network.NoInternetConnection)
        } catch (_: SocketTimeoutException) {
            Result.Error(AppError.Network.Timeout)
        } catch (e: HttpException) {
            val error = when (e.code()) {
                400 -> AppError.Network.BadRequest
                401 -> AppError.Authentication.Unauthorized
                404 -> AppError.Data.NOTFOUND
                408 -> AppError.Network.Timeout
                429 -> AppError.Network.TooManyRequests
                in 500..599 -> AppError.Network.ServerError
                else -> AppError.Network.UnexpectedResponse
            }
            Result.Error(error)
        } catch (_: IOException) {
            Result.Error(AppError.Network.NoInternetConnection)
        } catch (_: Exception) {
            Result.Error(AppError.Network.UnexpectedResponse)
        }
    }
}