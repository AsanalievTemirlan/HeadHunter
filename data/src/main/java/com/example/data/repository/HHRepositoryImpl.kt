package com.example.data.repository

import android.util.Log
import com.example.data.local.dao.HHDao
import com.example.data.local.entity.toDomain
import com.example.data.local.entity.toEntity
import com.example.data.network.api.HHApiService
import com.example.data.network.model.toDomain
import com.example.domain.either.Either
import com.example.domain.model.BaseResponseModel
import com.example.domain.model.Vacancy
import com.example.domain.repository.HHRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HHRepositoryImpl @Inject constructor(private val api: HHApiService, private val dao: HHDao) :
    HHRepository {
    override fun getData(): Flow<Either<String, BaseResponseModel>> = flow {
        val response = api.getHHData()
        if (response.isSuccessful && response.body() != null) {
            val baseResponseModel = response.body()!!.toDomain()
            emit(Either.Right(baseResponseModel))
        } else {
            emit(Either.Left("Ошибка при получении данных"))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left("Произошла ошибка: ${exception.localizedMessage}"))
    }


    override fun getDataRoom() = flow<Either<String, BaseResponseModel>>{
        emit(Either.Right(dao.getAllHH().toDomain()))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage?: "Error"))
    }

    override suspend fun insertData(data: BaseResponseModel) {
        withContext(Dispatchers.IO){
            dao.insertHH(data.toEntity())
        }
    }
    override suspend fun updateData(id: String, isFavorite: Boolean) {
        withContext(Dispatchers.IO){
            dao.updateIsFavorite(id, isFavorite)
        }
    }


}