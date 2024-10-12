package com.ono.medicinal.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ono.medicinal.data.local.db.dao.MedicineDao
import com.ono.medicinal.data.local.db.dao.UserDao
import com.ono.medicinal.data.local.entity.MedicineEntity
import com.ono.medicinal.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, MedicineEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun medicineDao(): MedicineDao
}
