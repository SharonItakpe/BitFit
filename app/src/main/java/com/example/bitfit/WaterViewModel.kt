package com.example.bitfit
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WaterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WaterRepository = WaterRepository(application.applicationContext)

    val allEntries: LiveData<List<WaterEntry>> = repository.allEntries

    fun insertEntry(amount: Int) = viewModelScope.launch {
        repository.insertEntry(WaterEntry(amount = amount))
    }

    fun clearEntries() = viewModelScope.launch {
        repository.deleteAllEntries()
    }
}
