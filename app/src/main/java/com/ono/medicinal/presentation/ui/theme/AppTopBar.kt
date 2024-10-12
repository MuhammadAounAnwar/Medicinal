package com.ono.medicinal.presentation.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun AppTopBar() {
    TopAppBar(
        title = { Text(text = "Medicine App") })
}
