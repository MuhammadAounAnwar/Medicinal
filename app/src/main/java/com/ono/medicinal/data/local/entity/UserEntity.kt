package com.ono.medicinal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ono.medicinal.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val username: String,
    val password: String
)

fun User.toEntity(): UserEntity {
    return UserEntity(username = this.username, password = this.password)
}

fun UserEntity.toDomainModel(): User {
    return User(username = this.username, password = this.password)
}
