package com.ono.medicinal.data.di

import com.ono.medicinal.domain.repository.MedicineRepository
import com.ono.medicinal.domain.usecase.GetMedicineDetailByNameUseCase
import com.ono.medicinal.domain.usecase.GetMedicineDetailByNameUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMedicineDetailByNameUseCase(medicineRepository: MedicineRepository): GetMedicineDetailByNameUseCase {
        return GetMedicineDetailByNameUseCaseImpl(medicineRepository)
    }

}