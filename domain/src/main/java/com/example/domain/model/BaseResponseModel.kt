package com.example.domain.model

data class BaseResponseModel(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)