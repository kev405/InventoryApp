package com.univalle.inventoryapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Inventory(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int): Serializable