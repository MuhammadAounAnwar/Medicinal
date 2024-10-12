package com.ono.medicinal

import com.ono.medicinal.data.local.db.dao.MedicineDao
import com.ono.medicinal.data.local.entity.toEntity
import com.ono.medicinal.data.remote.ApiService
import com.ono.medicinal.data.repository.MedicineRepositoryImpl
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.model.MedicineResponse
import com.ono.medicinal.domain.usecase.FetchAndSaveMedicinesUseCase
import com.ono.medicinal.domain.usecase.FetchMedicinesLocallyUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MedicineUseCaseTest {

    private val apiService: ApiService = mockk()
    private val medicineDao: MedicineDao = mockk()
    private lateinit var medicineRepository: MedicineRepositoryImpl
    private lateinit var fetchAndSaveMedicinesUseCase: FetchAndSaveMedicinesUseCase
    private lateinit var fetchMedicinesLocallyUseCase: FetchMedicinesLocallyUseCase


    @Before
    fun setup() {
        medicineRepository = MedicineRepositoryImpl(apiService, medicineDao)
        fetchAndSaveMedicinesUseCase = FetchAndSaveMedicinesUseCase(medicineRepository)
        fetchMedicinesLocallyUseCase = FetchMedicinesLocallyUseCase(medicineRepository)
    }

    @Test
    fun fetchAndStoreMedicines_shouldStoreMedicinesInDatabase() = runTest {
        val medicines = listOf(
            Medicine("Aspirin", "500mg", "Strength"),
            Medicine("Ibuprofen", "200mg", "Strength")
        )

        coEvery { apiService.getMedicines() } returns MedicineResponse(medicines)
        coEvery { medicineDao.insertAll(any()) } returns Unit
        coEvery { medicineDao.getAllMedicines() } returns flowOf(medicines.map { it.toEntity() })

        fetchAndSaveMedicinesUseCase.execute()

        val storedMedicines = mutableListOf<Medicine>()
        fetchMedicinesLocallyUseCase.execute().collect { list ->
            storedMedicines.addAll(list)
        }

        assertEquals(medicines.size, storedMedicines.size)
        assertEquals(medicines[0].medicine_name, storedMedicines[0].medicine_name)
    }

    @Test
    fun `fetchAndStoreMedicines should store medicines in the database`() = runTest {
        val medicines = listOf(
            Medicine("Aspirin", "500mg", "Strength"),
            Medicine("Ibuprofen", "200mg", "Strength")
        )
        coEvery { apiService.getMedicines() } returns MedicineResponse(medicines)
        coEvery { medicineDao.insertAll(any()) } returns Unit
        coEvery { medicineDao.getAllMedicines() } returns flowOf(medicines.map { it.toEntity() })

        // Act: Execute the use case to fetch and store medicines
        fetchAndSaveMedicinesUseCase.execute()

        // Collect the result from the Flow
        val storedMedicines = mutableListOf<Medicine>()
        fetchMedicinesLocallyUseCase.execute().collect { list ->
            storedMedicines.addAll(list)
        }

        // Assert: Verify that the medicines were stored correctly
        assertEquals(medicines.size, storedMedicines.size)
        assertEquals(medicines[0].medicine_name, storedMedicines[0].medicine_name)
    }


}
