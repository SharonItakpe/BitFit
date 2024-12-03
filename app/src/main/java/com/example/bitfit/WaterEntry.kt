package com.example.bitfit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_entries")
data class WaterEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Int, // Amount in ml
    val timestamp: Long = System.currentTimeMillis() // Time of the entry
)
