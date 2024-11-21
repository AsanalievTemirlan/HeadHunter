package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.HHDao
import com.example.data.local.db.HHDataBase
import com.example.data.network.api.HHApiService
import com.example.data.repository.HHRepositoryImpl
import com.example.data.utils.Constants.BASE_URL
import com.example.domain.repository.HHRepository
import com.example.domain.usecase.GetDataRoomUseCase
import com.example.domain.usecase.GetDataUseCase
import com.example.domain.usecase.InsertDataUseCase
import com.example.domain.usecase.UpdateDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        apiService: HHApiService,
        dao: HHDao
    ): HHRepository {
        return HHRepositoryImpl(apiService, dao)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, HHDataBase::class.java, "database"
    ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(database: HHDataBase) = database.HHDao()


    @Provides
    fun provideGetDataRoomUseCase(repository: HHRepository): GetDataRoomUseCase {
        return GetDataRoomUseCase(repository)
    }
    @Provides
    fun provideUpdateDataUseCase(repository: HHRepository): UpdateDataUseCase {
        return UpdateDataUseCase(repository)
    }
    @Provides
    fun provideInsertDataUseCase(repository: HHRepository): InsertDataUseCase {
        return InsertDataUseCase(repository)
    }


}