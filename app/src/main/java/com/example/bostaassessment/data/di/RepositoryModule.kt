package com.example.bostaassessment.data.di

import com.example.bostaassessment.data.api.SearchApiService
import com.example.bostaassessment.data.repository.SearchRepositoryImpl
import com.example.bostaassessment.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: SearchApiService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }
}