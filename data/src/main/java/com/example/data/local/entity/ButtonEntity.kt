package com.example.data.local.entity


import androidx.room.Entity
import com.example.domain.model.ButtonModel
import com.google.gson.annotations.SerializedName
@Entity("button")
data class ButtonEntity(
    @SerializedName("text")
    val text: String
)

fun ButtonEntity.toDomain(): ButtonModel = ButtonModel(text)

fun ButtonModel.toEntity(): ButtonEntity = ButtonEntity(text = text?: "")