package com.example.data.model


import com.example.domain.model.ButtonModel
import com.google.gson.annotations.SerializedName

data class ButtonDto(
    @SerializedName("text")
    val text: String?
)

fun ButtonDto.toDomain(): ButtonModel{
    return ButtonModel(text = text ?: "")
}

fun ButtonModel.toData(): ButtonDto{
    return ButtonDto(text = text?: "")
}