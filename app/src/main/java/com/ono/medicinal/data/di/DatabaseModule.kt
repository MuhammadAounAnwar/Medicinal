package com.ono.medicinal.data.di

import android.app.Application
import androidx.room.Room
import com.ono.medicinal.data.local.db.AppDatabase
import com.ono.medicinal.data.local.db.dao.MedicineDao
import com.ono.medicinal.data.local.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "medicinal_db").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideMedicineDao(db: AppDatabase): MedicineDao = db.medicineDao()
}
