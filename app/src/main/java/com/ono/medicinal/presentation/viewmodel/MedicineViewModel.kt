package com.ono.medicinal.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.usecase.FetchAndSaveMedicinesUseCase
import com.ono.medicinal.domain.usecase.FetchMedicinesLocallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val fetchMedicinesLocallyUseCase: FetchMedicinesLocallyUseCase, private val fetchAndSaveMedicinesUseCase: FetchAndSaveMedicinesUseCase
) : ViewModel() {

//    var medicineList by mutableStateOf<List<Medicine>>(emptyList())

    private val _medicineList = MutableStateFlow<List<Medicine>>(emptyList())
    val medicineList: StateFlow<List<Medicine>> = _medicineList.asStateFlow()

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    init {

        viewModelScope.launch {
            isLoading = true
            fetchAndSaveMedicinesUseCase.execute()
        }

        fetchMedicines()
    }

    private fun fetchMedicines() {
        viewModelScope.launch {
            fetchMedicinesLocallyUseCase.execute().collectLatest {
                _medicineList.value = it
                isLoading = false
            }
        }
    }
}
