package com.example.domain.usecase

import com.example.domain.model.BaseResponseModel
import com.example.domain.repository.HHRepository

class InsertDataUseCase(private val repository: HHRepository) {
    suspend operator fun invoke(data: BaseResponseModel) = repository.insertData(data)
}