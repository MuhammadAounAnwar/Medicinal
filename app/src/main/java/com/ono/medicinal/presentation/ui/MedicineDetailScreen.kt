package com.ono.medicinal.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ono.medicinal.presentation.viewmodel.MedicineDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(viewModel: MedicineDetailViewModel) {

    val medicine by viewModel.medicine.collectAsStateWithLifecycle()

    /*Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Medicine Detail") },
                navigationIcon = {
                    IconButton(onClick = { *//* Handle back navigation *//* }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

    }*/

    medicine?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Medicine Name: ${it.medicine_name}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Dose: ${it.dose}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Strength: ${it.strength}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))

            // Additional Medicine Details
            Text(text = "Instructions:", style = MaterialTheme.typography.headlineSmall)
            Text(text = "Take with food or as directed by a physician.")
        }

    }
}
