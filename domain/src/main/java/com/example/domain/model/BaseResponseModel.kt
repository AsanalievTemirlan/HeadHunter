package com.example.domain.model

data class BaseResponseModel(
    val offers: List<Offer>? = emptyList(),
    val vacancies: List<Vacancy>? = emptyList(),
)