package com.ono.medicinal.data.repository

import com.ono.medicinal.data.local.db.dao.UserDao
import com.ono.medicinal.data.local.entity.toDomainModel
import com.ono.medicinal.data.local.entity.toEntity
import com.ono.medicinal.domain.model.User
import com.ono.medicinal.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUser(username: String): User? {
        return userDao.getUser(username)?.toDomainModel()
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
}
