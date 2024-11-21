package com.example.data.local.entity


import androidx.room.Entity
import com.example.domain.model.Salary
import com.google.gson.annotations.SerializedName
@Entity("sal")
data class SalaryEntity(
    @SerializedName("full")
    val full: String,
    @SerializedName("short")
    val short: String
)

fun SalaryEntity.toDomain(): Salary = Salary(full, short)

fun Salary.toEntity(): SalaryEntity = SalaryEntity(full?: "", short?: "")