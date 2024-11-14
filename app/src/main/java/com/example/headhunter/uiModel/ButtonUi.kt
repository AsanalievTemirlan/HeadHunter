package com.example.headhunter.uiModel


import com.example.domain.model.ButtonModel
import com.google.gson.annotations.SerializedName

data class ButtonUi(
    @SerializedName("text")
    val text: String
)

fun ButtonUi.toDomain(): ButtonModel = ButtonModel(text)

fun ButtonModel.toUi(): ButtonUi = ButtonUi(text?: "")