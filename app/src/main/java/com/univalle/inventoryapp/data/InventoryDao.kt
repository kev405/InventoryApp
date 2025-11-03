package com.univalle.inventoryapp.data

import androidx.room.Dao
import androidx.room.Query
import com.univalle.inventoryapp.model.Inventory


@Dao
interface InventoryDao {

    @Query("SELECT * FROM Inventory")
    suspend fun getListInventory(): MutableList<Inventory>

}