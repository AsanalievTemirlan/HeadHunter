package com.example.headhunter.uiModel


import com.example.domain.model.BaseResponseModel
import com.google.gson.annotations.SerializedName

data class BaseResponseUi(
    @SerializedName("offers")
    val offerUis: List<OfferUi>,
    @SerializedName("vacancies")
    val vacancies: List<VacancyUi>
)

fun BaseResponseUi.toDomain(): BaseResponseModel =
    BaseResponseModel(offerUis.map { it.toDomain() }, vacancies.map { it.toDomain() })

fun BaseResponseModel.toUi(): BaseResponseUi =
    BaseResponseUi(offers!!.map { it.toUi() }, vacancies!!.map { it.toUi() })