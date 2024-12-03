package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WaterViewModel
    private lateinit var adapter: WaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WaterViewModel::class.java]
        adapter = WaterAdapter()

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.waterRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // TextViews for Average and Trend
        val averageConsumptionText: TextView = findViewById(R.id.averageConsumptionText)
        val trendText: TextView = findViewById(R.id.trendText)

        // Observe data and update RecyclerView and metrics
        viewModel.allEntries.observe(this) { entries ->
            adapter.updateEntries(entries)
            updateMetrics(entries, averageConsumptionText, trendText)
        }

        // Setup EditText and Button
        val waterAmountEditText: EditText = findViewById(R.id.waterAmountEditText)
        val saveEntryButton: Button = findViewById(R.id.saveEntryButton)

        saveEntryButton.setOnClickListener {
            val inputText = waterAmountEditText.text.toString()
            val amount = inputText.toIntOrNull()

            if (amount != null && amount > 0) {
                viewModel.insertEntry(amount)
                waterAmountEditText.text.clear() // Clear input after saving
                Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show()
            } else {
                waterAmountEditText.error = "Please enter a valid amount"
            }
        }
    }

    /**
     * Updates the average consumption and trend based on the list of water entries.
     */
    private fun updateMetrics(
        entries: List<WaterEntry>,
        averageConsumptionText: TextView,
        trendText: TextView
    ) {
        // Calculate the average consumption
        val totalConsumption = entries.sumOf { it.amount }
        val averageConsumption = if (entries.isNotEmpty()) totalConsumption / entries.size else 0

        // Determine the trend (compare the last two entries)
        val trend = when {
            entries.size > 1 -> {
                val latest = entries[0].amount
                val secondLatest = entries[1].amount
                when {
                    latest > secondLatest -> "Increasing"
                    latest < secondLatest -> "Decreasing"
                    else -> "Stable"
                }
            }
            else -> "No trend data"
        }

        // Update the TextViews
        averageConsumptionText.text = "Average consumption: $averageConsumption ml"
        trendText.text = "Trend: $trend"
    }
}

