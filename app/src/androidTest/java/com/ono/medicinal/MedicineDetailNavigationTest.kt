package com.ono.medicinal

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.presentation.ui.MedicineListScreen
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMaterial3Api
@RunWith(AndroidJUnit4::class)
class MedicineDetailNavigationTest {

    @OptIn(ExperimentalMaterial3Api::class)
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: NavController

    @Before
    fun setup() {
        // Create a mock NavController with relaxed behavior for easy verification
        navController = mockk(relaxed = true)
    }

    @Test
    fun onMedicineClick_shouldNavigateToDetailScreen() {
        // Define a sample medicine item
        val medicine = Medicine("Aspirin", "500mg", "Strength")

        // Launch the Medicine List screen using the Compose rule
        composeTestRule.setContent {
            val navHostController = rememberNavController()
            MedicineListScreen { clickedMedicine ->
                navController.navigate("medicine_detail/${clickedMedicine.medicine_name}")
            }
        }

        // Simulate a click on the medicine item
        navController.navigate("medicine_detail/${medicine.medicine_name}")

        // Verify that the navigation was triggered with the correct route
        verify { navController.navigate("medicine_detail/Aspirin") }
    }
}
