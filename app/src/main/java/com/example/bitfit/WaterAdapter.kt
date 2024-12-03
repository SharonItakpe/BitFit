package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter : RecyclerView.Adapter<WaterAdapter.WaterViewHolder>() {

    private var entries = emptyList<WaterEntry>()

    class WaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amountText: TextView = itemView.findViewById(R.id.waterAmountTextView)
        val timestampText: TextView = itemView.findViewById(R.id.waterTimestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_water_entry, parent, false)
        return WaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        val entry = entries[position]

        // Use string resources with placeholders for better localization
        holder.amountText.text = holder.itemView.context.getString(R.string.water_entry, entry.amount)

        // Format the timestamp
        holder.timestampText.text = android.text.format.DateFormat.format("hh:mm a", entry.timestamp)
    }

    override fun getItemCount() = entries.size

    fun updateEntries(newEntries: List<WaterEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}

