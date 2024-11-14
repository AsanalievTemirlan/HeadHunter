package com.example.data.model


import com.example.domain.model.Vacancy
import com.google.gson.annotations.SerializedName

data class VacancyDto(
    @SerializedName("address")
    val address: AddressDto,
    @SerializedName("appliedNumber")
    val appliedNumber: Int,
    @SerializedName("company")
    val company: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("experience")
    val experienceDto: ExperienceDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("lookingNumber")
    val lookingNumber: Int,
    @SerializedName("publishedDate")
    val publishedDate: String,
    @SerializedName("questions")
    val questions: List<String>,
    @SerializedName("responsibilities")
    val responsibilities: String,
    @SerializedName("salary")
    val salaryDto: SalaryDto,
    @SerializedName("schedules")
    val schedules: List<String>,
    @SerializedName("title")
    val title: String
)

fun VacancyDto.toDomain(): Vacancy = Vacancy(
    address.toDomain(),
    appliedNumber,
    company,
    description,
    experienceDto.toDomain(),
    id,
    isFavorite,
    lookingNumber,
    publishedDate,
    questions,
    responsibilities,
    salaryDto.toDomain(),
    schedules,
    title
)

fun Vacancy.toData(): VacancyDto = VacancyDto(
    address.toData(),
    appliedNumber?: 0,
    company?: "",
    description?: "",
    experience.toData(),
    id?: "",
    isFavorite?: false,
    lookingNumber?: 0,
    publishedDate?: "",
    questions?: emptyList(),
    responsibilities?: "",
    salary.toData() ,
    schedules?: emptyList(),
    title?: ""
)