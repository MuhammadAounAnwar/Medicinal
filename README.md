# Medicine Management App

![License](https://img.shields.io/github/license/your-repo-name/medicine-management-app)  
![Build Status](https://img.shields.io/github/actions/workflow/status/your-repo-name/ci.yml)

## 🚀 Overview
The Medicine Management App is a comprehensive solution for managing medicines, providing functionalities to fetch, store, and display medicine details effectively.

## 📋 Table of Contents
- [Architecture](#architecture)
- [MVVM Pattern](#mvvm-pattern)
- [Clean Architecture](#clean-architecture)
- [Classes Explanation](#classes-explanation)
- [Unit Testing](#unit-testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🏗 Architecture
This project is built using the **MVVM** (Model-View-ViewModel) architecture and **Clean Architecture** principles, ensuring separation of concerns and testability.

### MVVM Pattern
- **Model:** Represents the data layer, including API calls and database interactions.
- **View:** The UI layer responsible for displaying data and user interactions.
- **ViewModel:** Acts as a bridge between the Model and the View, handling business logic and state management.

### Clean Architecture
- **Presentation Layer:** Contains UI-related code, ViewModels, and navigation components.
- **Domain Layer:** Includes use cases that encapsulate business logic and interact with the repository.
- **Data Layer:** Manages data sources, such as remote API and local database, implementing the repository pattern.

## 📦 Classes Explanation
- **Models:** 
  - `Medicine`: Represents the medicine data structure.
  - `MedicineEntity`: Represents the database entity for the medicine.

- **ViewModels:**
  - `MedicineViewModel`: Handles UI-related data and communicates with use cases to fetch and store medicines.

- **Use Cases:**
  - `FetchAndSaveMedicinesUseCase`: Fetches medicines from an API and saves them to the database.
  - `FetchMedicinesLocallyUseCase`: Retrieves stored medicines from the local database.

- **Repositories:**
  - `MedicineRepository`: Interacts with data sources to manage medicine data.

## 🧪 Unit Testing
Unit tests are implemented using **JUnit** and **Mockk** for mocking dependencies:
- **Test Cases:**
  - **Fetch and Store Medicines:** Verifies that medicines fetched from the API are stored in the database correctly.
  - **Retrieve Medicines:** Ensures that the retrieved medicines from the database match the expected results.
  - **Navigation Tests:** Confirms that navigation actions trigger the correct transitions in the UI.

Example of a test case for fetching medicines:
```kotlin
@Test
fun fetchAndStoreMedicines_shouldStoreMedicinesInDatabase() = runBlockingTest {
    // Mock the API response
    val medicines = listOf(
        Medicine("Aspirin", "500mg", "Strength"),
        Medicine("Ibuprofen", "200mg", "Strength")
    )

    coEvery { apiService.getMedicines() } returns MedicineResponse(medicines)
    coEvery { medicineDao.insertAll(any()) } returns Unit

    fetchAndSaveMedicinesUseCase.execute()

    val storedMedicines = fetchMedicinesLocallyUseCase.execute().toList()
    assertEquals(medicines.size, storedMedicines.size)
    assertEquals(medicines[0].name, storedMedicines[0].name)
}
