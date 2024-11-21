package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.BaseResponseModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = "baseResponseEntity")
data class BaseResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("offers")
    val offerEntities: List<OfferEntity>,
    @SerializedName("vacancies")
    val vacancies: List<VacancyEntity>
)

fun BaseResponseEntity.toDomain(): BaseResponseModel = BaseResponseModel(
    offers = offerEntities.map { it.toDomain() }?: emptyList(),
    vacancies = vacancies.map { it.toDomain() }?: emptyList()
)

fun BaseResponseModel.toEntity(): BaseResponseEntity = BaseResponseEntity(
    id = 0,
    offerEntities = offers!!.map { it.toEntity() },
    vacancies = vacancies!!.map { it.toEntity() })