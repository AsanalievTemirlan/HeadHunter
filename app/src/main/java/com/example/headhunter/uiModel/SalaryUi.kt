package com.example.headhunter.uiModel


import com.example.domain.model.Salary
import com.google.gson.annotations.SerializedName

data class SalaryUi(
    @SerializedName("full")
    val full: String,
    @SerializedName("short")
    val short: String
)

fun SalaryUi.toDomain(): Salary = Salary(full, short)

fun Salary.toUi(): SalaryUi = SalaryUi(full?: "", short?: "")