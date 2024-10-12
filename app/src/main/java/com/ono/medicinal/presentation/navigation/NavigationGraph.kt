package com.ono.medicinal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ono.medicinal.presentation.ui.LoginScreen
import com.ono.medicinal.presentation.ui.MedicineDetailScreen
import com.ono.medicinal.presentation.ui.MedicineListScreen
import com.ono.medicinal.presentation.viewmodel.LoginViewModel
import com.ono.medicinal.presentation.viewmodel.MedicineDetailViewModel
import com.ono.medicinal.presentation.viewmodel.MedicineViewModel

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = "login", modifier = modifier) {
        composable("login") {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(loginViewModel) { username ->
                navController.navigate("medicines")
            }
        }
        composable("medicines") {
            val medicineViewModel: MedicineViewModel = hiltViewModel()
            MedicineListScreen(medicineViewModel) { medicine ->
                navController.navigate("medicine_detail/${medicine.medicine_name}")
            }
        }

        composable(
            route = "medicine_detail/{medicineName}",
            arguments = listOf(navArgument("medicineName") { type = NavType.StringType })
        ) { backStackEntry ->
            val medicineName = backStackEntry.arguments?.getString("medicineName")
            val viewModel: MedicineDetailViewModel = hiltViewModel()
            LaunchedEffect(medicineName) {
                medicineName?.let { viewModel.getMedicineDetails(it) }
            }
            MedicineDetailScreen(viewModel = viewModel)
        }

    }
}
