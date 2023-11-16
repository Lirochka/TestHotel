package com.example.testhotel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.model.room_hotel.RoomModel
import com.example.testhotel.common.formatPrice
import com.example.testhotel.databinding.ItemHotelRoomBinding

class RoomsListAdapter : ListAdapter<RoomModel, RoomItemViewHolder>(
    RoomItemCallback()
) {

    var onChooseRoomClickListener: OnChooseRoomClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomItemViewHolder {
        val binding = ItemHotelRoomBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RoomItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomItemViewHolder, position: Int) {
        val roomItem = getItem(position)
        with(holder.binding) {
            with(roomItem) {
                roomName.text = name ?: ""
                roomPeculiarities1.text = peculiarities.getOrNull(0) ?: ""
                roomPeculiarities2.text = peculiarities.getOrNull(1) ?: ""
                roomPrice.text = "${price?.let { it1 -> formatPrice(it1) }} ₽"
                roomPricePer.text = pricePer ?: ""

                val slideModels = imageUrls.map { url ->
                    SlideModel(url)
                }
                roomImageSlider.setImageList(slideModels)

                roomButton.setOnClickListener {
                    onChooseRoomClickListener?.onRoomClick()
                }
            }
        }
    }

    interface OnChooseRoomClickListener {
        fun onRoomClick()
    }
}