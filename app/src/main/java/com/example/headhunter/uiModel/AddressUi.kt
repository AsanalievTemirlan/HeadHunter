package com.example.headhunter.uiModel


import com.example.domain.model.Address
import com.google.gson.annotations.SerializedName

data class AddressUi(
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("town")
    val town: String
)

fun AddressUi.toDomain(): Address = Address(house, street, town)

fun Address.toUi(): AddressUi = AddressUi(house?: "", street?: "", town?: "")