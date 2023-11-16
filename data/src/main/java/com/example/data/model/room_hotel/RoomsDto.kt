package com.example.data.model.room_hotel

import com.example.data.model.room_hotel.RoomDto
import com.google.gson.annotations.SerializedName


data class RoomsDto (

  @SerializedName("rooms" ) var roomsDto : ArrayList<RoomDto> = arrayListOf()
)