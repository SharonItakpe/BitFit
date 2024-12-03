package com.example.bitfit
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterEntryDao {
    @Query("SELECT * FROM water_entries ORDER BY timestamp DESC")
    fun getAllEntries(): LiveData<List<WaterEntry>>

    @Insert
    suspend fun insertEntry(entry: WaterEntry)

    @Query("DELETE FROM water_entries")
    suspend fun deleteAllEntries()
}
