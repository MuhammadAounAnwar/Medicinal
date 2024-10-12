package com.ono.medicinal.domain.repository

import com.ono.medicinal.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun fetchAndSaveMedicines()
    suspend fun getMedicinesRemotely(): List<Medicine>
    suspend fun getMedicinesLocally(): Flow<List<Medicine>>
    suspend fun getMedicineByName(name: String): Medicine
}
