package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.BaseResponseEntity
import com.example.data.local.entity.VacancyEntity

@Dao
interface HHDao {
    @Query("SELECT * FROM baseResponseEntity")
    fun getAllHH(): BaseResponseEntity


    @Insert
    suspend fun insertHH(model: BaseResponseEntity)

    @Query("UPDATE vacancyEntity SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateIsFavorite(id: String, isFavorite: Boolean)

}