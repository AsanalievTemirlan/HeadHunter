package com.example.domain.usecase

import com.example.domain.model.BaseResponseModel
import com.example.domain.model.Vacancy
import com.example.domain.repository.HHRepository

class UpdateDataUseCase(private val repository: HHRepository) {
    suspend operator fun invoke(id: String, isFavorite: Boolean) = repository.updateData(id, isFavorite)
}