package com.example.bitfit

import android.content.Context
import androidx.lifecycle.LiveData

class WaterRepository(private val context: Context) {
    private val dao = AppDatabase.getInstance(context).waterEntryDao()

    val allEntries: LiveData<List<WaterEntry>> = dao.getAllEntries()

    suspend fun insertEntry(entry: WaterEntry) {
        dao.insertEntry(entry)
    }

    suspend fun deleteAllEntries() {
        dao.deleteAllEntries()
    }
}

