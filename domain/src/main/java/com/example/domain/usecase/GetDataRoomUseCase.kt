package com.example.domain.usecase

import com.example.domain.repository.HHRepository

class GetDataRoomUseCase(private val repository: HHRepository) {
    operator fun invoke() = repository.getDataRoom()
}