package com.ono.medicinal.data.remote

import com.ono.medicinal.domain.model.Medicine
import com.ono.medicinal.domain.model.MedicineResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v3/27d43f33-049c-435e-beb2-f3e094698f18")
    suspend fun getMedicines(): MedicineResponse
}
