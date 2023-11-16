package com.example.testhotel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.add_tourist.Tourist

class TouristItemCallback: DiffUtil.ItemCallback<Tourist>() {

    override fun areItemsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem.title == oldItem.title
    }

    override fun areContentsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem == newItem
    }
}