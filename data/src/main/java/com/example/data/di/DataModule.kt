package com.example.data.di

import com.example.data.api.HHApiService
import com.example.data.repository.HHRepositoryImpl
import com.example.data.utils.Constants.BASE_URL
import com.example.domain.repository.HHRepository
import com.example.domain.usecase.GetDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): HHApiService {
        return retrofit.create(HHApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideGetDataUseCase(
        repository: HHRepository
    ): GetDataUseCase {
        return GetDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideHHRepository(
        apiService: HHApiService
    ): HHRepository {
        return HHRepositoryImpl(apiService)
    }


}