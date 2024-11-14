package com.example.domain.usecase

import com.example.domain.repository.HHRepository

class GetDataUseCase (private val repository: HHRepository) {
    operator fun invoke() = repository.getData()
}