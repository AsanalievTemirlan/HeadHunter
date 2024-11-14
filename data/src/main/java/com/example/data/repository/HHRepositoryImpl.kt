package com.example.data.repository

import com.example.domain.either.Either
import com.example.data.api.HHApiService
import com.example.data.model.toDomain
import com.example.domain.model.BaseResponseModel
import com.example.domain.repository.HHRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HHRepositoryImpl @Inject constructor(private val api: HHApiService) : HHRepository {
    override fun getData(): Flow<Either<String, BaseResponseModel>> = flow {
        try {
            val response = api.getHHData()
            if (response.isSuccessful && response.body() != null) {
                val baseResponseModel = response.body()!!.toDomain()
                emit(Either.Right(baseResponseModel))
            } else {
                emit(Either.Left("Ошибка при получении данных"))
            }
        } catch (e: Exception) {
            emit(Either.Left("Произошла ошибка: ${e.localizedMessage}"))
        }
    }
}