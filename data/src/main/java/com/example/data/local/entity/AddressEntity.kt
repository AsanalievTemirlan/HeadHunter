package com.example.data.local.entity


import androidx.room.Entity
import com.example.domain.model.Address
import com.google.gson.annotations.SerializedName

@Entity(tableName = "address")
data class AddressEntity(
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("town")
    val town: String
)

fun AddressEntity.toDomain(): Address = Address(house, street, town)

fun Address.toEntity(): AddressEntity = AddressEntity(house ?: "", street ?: "", town ?: "")