package com.ono.medicinal.domain.usecase

import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchAndSaveMedicinesUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend fun execute(): Result<Unit> {
        return try {
            val medicines = medicineRepository.fetchAndSaveMedicines()
            Result.success(medicines)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class FetchMedicineRemotelyUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend fun execute(): Result<List<Medicine>> {
        return try {
            val medicines = medicineRepository.getMedicinesRemotely()
            Result.success(medicines)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class FetchMedicinesLocallyUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend fun execute(): Flow<List<Medicine>> {
        return medicineRepository.getMedicinesLocally()
    }
}