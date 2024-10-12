package com.ono.medicinal.domain.usecase

import com.ono.medicinal.domain.model.Medicine

interface GetMedicineDetailByNameUseCase {
    suspend operator fun invoke(medicineName: String): Medicine?
}
