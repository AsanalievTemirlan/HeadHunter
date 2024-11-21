package com.example.domain.repository

import com.example.domain.either.Either
import com.example.domain.model.BaseResponseModel
import com.example.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface HHRepository {
    fun getData(): Flow<Either<String, BaseResponseModel>>

    fun getDataRoom(): Flow<Either<String, BaseResponseModel>>

    suspend fun insertData(data: BaseResponseModel)

    suspend fun updateData(id: String, isFavorite: Boolean)
}