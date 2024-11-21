package com.example.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Vacancy
import com.google.gson.annotations.SerializedName
@Entity(tableName = "vacancyEntity")
data class VacancyEntity(
    @SerializedName("address")
    val addressEntity: AddressEntity,
    @SerializedName("appliedNumber")
    val appliedNumber: Int,
    @SerializedName("company")
    val company: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("experience")
    val experienceEntity: ExperienceEntity,
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
    val salaryEntity: SalaryEntity,
    @SerializedName("schedules")
    val schedules: List<String>,
    @SerializedName("title")
    val title: String,
    @PrimaryKey(true)
    val idRoom: Int = 0
)

fun VacancyEntity.toDomain(): Vacancy = Vacancy(
    address = addressEntity.toDomain(),
    appliedNumber = appliedNumber,
    company = company,
    description = description,
    experience = experienceEntity.toDomain(),
    id = id,
    isFavorite = isFavorite,
    lookingNumber = lookingNumber,
    publishedDate = publishedDate,
    questions = questions,
    responsibilities = responsibilities,
    salary = salaryEntity.toDomain(),
    schedules = schedules,
    title = title
)

fun Vacancy.toEntity(): VacancyEntity = VacancyEntity(
    addressEntity = address.toEntity(),
    appliedNumber = appliedNumber?: 0,
    company = company?: "",
    description = description?: "",
    experienceEntity = experience.toEntity(),
    id = id ?: "",
    isFavorite = isFavorite?: false,
    lookingNumber = lookingNumber?: 0,
    publishedDate = publishedDate?: "",
    questions = questions?: emptyList(),
    responsibilities = responsibilities?: "",
    salaryEntity = salary.toEntity(),
    schedules = schedules?: emptyList(),
    title = title?: "",
)
