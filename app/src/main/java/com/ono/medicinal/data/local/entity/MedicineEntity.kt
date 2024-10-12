package com.ono.medicinal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ono.medicinal.domain.model.Medicine

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey val medicine_name: String,
    val dose: String,
    val strength: String
)

fun MedicineEntity.toDomainModel(): Medicine {
    return Medicine(medicine_name = this.medicine_name, dose = this.dose, strength = this.strength)
}

fun Medicine.toEntity(): MedicineEntity {
    return MedicineEntity(medicine_name = this.medicine_name, dose = this.dose, strength = this.strength)
}

fun List<Medicine>.toEntityList(): List<MedicineEntity> {
    return this.map { it.toEntity() }
}

fun List<MedicineEntity>.toDomainList(): List<Medicine> {
    return this.map { it.toDomainModel() }
}
