package com.ono.medicinal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.usecase.GetMedicineDetailByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineDetailViewModel @Inject constructor(private val getMedicineDetailByNameUseCase: GetMedicineDetailByNameUseCase) : ViewModel() {
    private val _medicine = MutableStateFlow<Medicine?>(null)
    val medicine: StateFlow<Medicine?> = _medicine.asStateFlow()

    fun getMedicineDetails(medicineName: String) {
        viewModelScope.launch {
            val medicineDetails = getMedicineDetailByNameUseCase(medicineName)
            _medicine.value = medicineDetails
        }
    }
}