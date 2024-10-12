package com.ono.medicinal.data.di

import com.ono.medicinal.data.local.db.dao.MedicineDao
import com.ono.medicinal.data.local.db.dao.UserDao
import com.ono.medicinal.data.remote.ApiService
import com.ono.medicinal.data.repository.MedicineRepositoryImpl
import com.ono.medicinal.data.repository.UserRepositoryImpl
import com.ono.medicinal.domain.repository.MedicineRepository
import com.ono.medicinal.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideMedicineRepository(apiService: ApiService, medicineDao: MedicineDao): MedicineRepository {
        return MedicineRepositoryImpl(apiService, medicineDao)
    }
}