package com.example.headhunter.uiModel


import com.example.domain.model.Experience
import com.google.gson.annotations.SerializedName

data class ExperienceUi(
    @SerializedName("previewText")
    val previewText: String,
    @SerializedName("text")
    val text: String
)

fun ExperienceUi.toDomain(): Experience = Experience(previewText, text)

fun Experience.toUi(): ExperienceUi = ExperienceUi(previewText?: "", text?: "")