package com.ono.medicinal.domain.usecase

import com.ono.medicinal.domain.repository.MedicineRepository
import com.ono.medicinal.domain.model.Medicine
import javax.inject.Inject

class GetMedicineDetailByName @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend fun execute(name: String): Result<Medicine> {
        return try {
            val medicines = medicineRepository.getMedicineByName(name)
            Result.success(medicines)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}