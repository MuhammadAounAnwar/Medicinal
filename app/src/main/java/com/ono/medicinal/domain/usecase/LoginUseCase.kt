package com.ono.medicinal.domain.usecase

import com.ono.medicinal.data.local.entity.UserEntity
import com.ono.medicinal.data.local.entity.toDomainModel
import com.ono.medicinal.domain.repository.UserRepository
import com.ono.medicinal.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(username: String, password: String): Result<User> {
        return if (username.isNotEmpty() && password.length >= 6) {
//            val user = userRepository.getUser(username)
            val user = UserEntity(username, password).toDomainModel()
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid username or password"))
            }
        } else {
            Result.failure(Exception("Please enter valid credentials"))
        }
    }
}
