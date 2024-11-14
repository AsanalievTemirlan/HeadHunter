package com.example.domain.repository

import com.example.domain.either.Either
import com.example.domain.model.BaseResponseModel
import kotlinx.coroutines.flow.Flow

interface HHRepository {
    fun getData(): Flow<Either<String, BaseResponseModel>>
}