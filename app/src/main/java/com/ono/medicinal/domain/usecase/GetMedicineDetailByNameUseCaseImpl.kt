package com.ono.medicinal.domain.usecase

import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.repository.MedicineRepository
import javax.inject.Inject

class GetMedicineDetailByNameUseCaseImpl @Inject constructor(
    private val repository: MedicineRepository
) : GetMedicineDetailByNameUseCase {

    override suspend fun invoke(medicineName: String): Medicine {
        return repository.getMedicineByName(medicineName)
    }
}
