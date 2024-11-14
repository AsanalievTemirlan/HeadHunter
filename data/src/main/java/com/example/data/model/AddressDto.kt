package com.example.data.model


import com.example.domain.model.Address
import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("town")
    val town: String
)

fun AddressDto.toDomain(): Address {
    return Address(
        house = house,
        street = street,
        town = town
    )
}
fun Address.toData(): AddressDto {
    return AddressDto(
        house = house?: "",
        street = street?: "",
        town = town?: ""
    )
}