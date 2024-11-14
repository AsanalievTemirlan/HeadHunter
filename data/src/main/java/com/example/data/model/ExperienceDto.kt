package com.example.data.model


import com.example.domain.model.Experience
import com.google.gson.annotations.SerializedName

data class ExperienceDto(
    @SerializedName("previewText")
    val previewText: String,
    @SerializedName("text")
    val text: String
)

fun ExperienceDto.toDomain(): Experience {
    return Experience(previewText = previewText, text = text)
}

fun Experience.toData(): ExperienceDto = ExperienceDto(previewText = previewText, text = text)