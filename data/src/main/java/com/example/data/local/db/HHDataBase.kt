package com.example.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.HHDao
import com.example.data.local.entity.BaseResponseEntity
import com.example.data.local.entity.VacancyEntity
import com.example.data.utils.Converters

@Database(entities = [BaseResponseEntity::class, VacancyEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class HHDataBase: RoomDatabase() {
    abstract fun HHDao(): HHDao
}