package com.example.data.utils

import androidx.room.TypeConverter
import com.example.data.local.entity.AddressEntity
import com.example.data.local.entity.ExperienceEntity
import com.example.data.local.entity.OfferEntity
import com.example.data.local.entity.SalaryEntity
import com.example.data.local.entity.VacancyEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromOfferEntityList(value: List<OfferEntity>): String = gson.toJson(value)

    @TypeConverter
    fun toOfferEntityList(value: String): List<OfferEntity> =
        gson.fromJson(value, object : TypeToken<List<OfferEntity>>() {}.type)

    @TypeConverter
    fun fromVacancyEntityList(value: List<VacancyEntity>): String = gson.toJson(value)

    @TypeConverter
    fun toVacancyEntityList(value: String): List<VacancyEntity> =
        gson.fromJson(value, object : TypeToken<List<VacancyEntity>>() {}.type)

    @TypeConverter
    fun fromAddressEntity(value: AddressEntity): String = gson.toJson(value)

    @TypeConverter
    fun toAddressEntity(value: String): AddressEntity =
        gson.fromJson(value, object : TypeToken<AddressEntity>() {}.type)

    @TypeConverter
    fun fromSalaryEntity(value: SalaryEntity): String = gson.toJson(value)

    @TypeConverter
    fun toSalaryEntity(value: String): SalaryEntity =
        gson.fromJson(value, object : TypeToken<SalaryEntity>() {}.type)

    @TypeConverter
    fun fromExperienceEntity(value: ExperienceEntity): String = gson.toJson(value)

    @TypeConverter
    fun toExperienceEntity(value: String): ExperienceEntity =
        gson.fromJson(value, object : TypeToken<ExperienceEntity>() {}.type)

    @TypeConverter
    fun fromStringList(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> =
        Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)

}
