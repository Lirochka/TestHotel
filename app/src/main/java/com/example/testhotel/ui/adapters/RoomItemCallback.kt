package com.example.testhotel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.room_hotel.RoomModel

class RoomItemCallback: DiffUtil.ItemCallback<RoomModel>() {

    override fun areItemsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomModel, newItem: RoomModel): Boolean {
        return oldItem == newItem
    }
}