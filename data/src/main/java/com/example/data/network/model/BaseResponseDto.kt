package com.example.data.network.model


import com.example.domain.model.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class BaseResponseDto(
    @SerializedName("offers")
    val offerDtos: List<OfferDto>,
    @SerializedName("vacancies")
    val vacancies: List<VacancyDto>
)

fun BaseResponseDto.toDomain(): BaseResponseModel{
    return BaseResponseModel(
        offerDtos.map { it.toDomain() },
        vacancies.map { it.toDomain() }
    )
}

fun BaseResponseModel.toData(): BaseResponseDto = BaseResponseDto(offers!!.map { it.toData()}, vacancies!!.map { it.toData()})