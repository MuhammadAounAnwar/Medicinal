package com.ono.medicinal.data.repository

import com.ono.medicinal.data.local.db.dao.MedicineDao
import com.ono.medicinal.data.local.entity.toDomainModel
import com.ono.medicinal.data.local.entity.toEntityList
import com.ono.medicinal.data.remote.ApiService
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val medicineDao: MedicineDao
) : MedicineRepository {
    override suspend fun fetchAndSaveMedicines() {
        apiService.getMedicines().medicines.run {
            medicineDao.insertAll(this.toEntityList())
        }
    }

    override suspend fun getMedicinesRemotely(): List<Medicine> {
        return apiService.getMedicines().medicines
    }

    override suspend fun getMedicinesLocally(): Flow<List<Medicine>> {
        return medicineDao.getAllMedicines().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getMedicineByName(name: String): Medicine {
        return medicineDao.getMedicineByName(name)
    }
}
