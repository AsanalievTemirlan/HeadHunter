package com.example.headhunter.uiModel


import com.example.domain.model.Vacancy
import com.google.gson.annotations.SerializedName

data class VacancyUi(
    @SerializedName("address")
    val addressUi: AddressUi,
    @SerializedName("appliedNumber")
    val appliedNumber: Int,
    @SerializedName("company")
    val company: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("experience")
    val experienceUi: ExperienceUi,
    @SerializedName("id")
    val id: String,
    @SerializedName("isFavorite")
    var isFavorite: Boolean,
    @SerializedName("lookingNumber")
    val lookingNumber: Int,
    @SerializedName("publishedDate")
    val publishedDate: String,
    @SerializedName("questions")
    val questions: List<String>,
    @SerializedName("responsibilities")
    val responsibilities: String,
    @SerializedName("salary")
    val salaryUi: SalaryUi,
    @SerializedName("schedules")
    val schedules: List<String>,
    @SerializedName("title")
    val title: String
)

fun VacancyUi.toDomain(): Vacancy = Vacancy(
    address = addressUi.toDomain(),
    appliedNumber = appliedNumber,
    company = company,
    description = description,
    experience = experienceUi.toDomain(),
    id = id,
    isFavorite = isFavorite,
    lookingNumber = lookingNumber,
    publishedDate = publishedDate,
    questions = questions,
    responsibilities = responsibilities,
    salary = salaryUi.toDomain(),
    schedules = schedules,
    title = title
)

fun Vacancy.toUi() : VacancyUi = VacancyUi(
    addressUi = address.toUi(),
    appliedNumber = appliedNumber?: 0,
    company = company?: "",
    description = description?: "",
    experienceUi = experience.toUi(),
    id = id?: "",
    isFavorite = isFavorite?: false,
    lookingNumber = lookingNumber?: 0,
    publishedDate = publishedDate?: "",
    questions = questions?: emptyList(),
    responsibilities = responsibilities?: "",
    salaryUi = salary.toUi(),
    schedules = schedules?: emptyList(),
    title = title?: ""
)