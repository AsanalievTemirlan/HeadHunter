package com.example.data.local.entity


import androidx.room.Entity
import com.example.domain.model.Experience
import com.google.gson.annotations.SerializedName
@Entity("experience")
data class ExperienceEntity(
    @SerializedName("previewText")
    val previewText: String,
    @SerializedName("text")
    val text: String
)

fun ExperienceEntity.toDomain(): Experience = Experience(previewText, text)

fun Experience.toEntity(): ExperienceEntity = ExperienceEntity(previewText?: "", text?: "")