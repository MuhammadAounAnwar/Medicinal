package com.ono.medicinal.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.presentation.viewmodel.MedicineViewModel

@Composable
fun MedicineListScreen(viewModel: MedicineViewModel = hiltViewModel(), onMedicineClick: (Medicine) -> Unit) {
    val medicines by viewModel.medicineList.collectAsStateWithLifecycle()

    LazyColumn {
        items(medicines) { medicine ->
            MedicineCard(medicine) {
                onMedicineClick(medicine)
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medicine.medicine_name}")
            Text(text = "Dose: ${medicine.dose}")
            Text(text = "Strength: ${medicine.strength}")
        }
    }
}
