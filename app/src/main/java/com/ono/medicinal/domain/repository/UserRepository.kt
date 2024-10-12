package com.ono.medicinal.domain.repository

import com.ono.medicinal.domain.model.User

interface UserRepository {
    suspend fun getUser(username: String): User?
    suspend fun saveUser(user: User)
}
