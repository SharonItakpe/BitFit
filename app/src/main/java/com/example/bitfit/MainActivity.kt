package com.example.bitfit

import android.os.Bundle
import android.widget.Button
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

        viewModel = ViewModelProvider(this)[WaterViewModel::class.java] // Updated for modern syntax
        adapter = WaterAdapter()

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.waterRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe data
        viewModel.allEntries.observe(this) { entries ->
            adapter.updateEntries(entries)
        }

        // Add Entry Button
        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener {
            // Add a dummy entry (replace with actual input later)
            viewModel.insertEntry(500)
        }
    }
}

