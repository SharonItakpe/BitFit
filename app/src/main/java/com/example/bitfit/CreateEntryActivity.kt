package com.example.bitfit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bitfit.databinding.ActivityCreateEntryBinding

class CreateEntryActivity : AppCompatActivity() {
    private lateinit var viewModel: WaterViewModel
    private lateinit var binding: ActivityCreateEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[WaterViewModel::class.java]

        // Set up the save button
        binding.saveEntryButton.setOnClickListener {
            val inputText = binding.waterInputEditText.text.toString()
            val amount = inputText.toIntOrNull()
            if (!inputText.isBlank() && amount != null && amount > 0) {
                viewModel.insertEntry(amount)
                finish() // Go back to the main activity
            } else {
                binding.waterInputEditText.error = "Please enter a valid amount"
            }
        }
    }
}
