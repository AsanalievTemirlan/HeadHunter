package com.example.data.model


import com.example.domain.model.Salary
import com.google.gson.annotations.SerializedName

data class SalaryDto(
    @SerializedName("full")
    val full: String,
    @SerializedName("short")
    val short: String
)

fun SalaryDto.toDomain(): Salary = Salary(full = full, short = short)
fun Salary.toData(): SalaryDto =SalaryDto(full = full?: "", short = short?: "")