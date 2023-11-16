package com.example.data.mapper.room_hotel

import com.example.data.mapper.Mapper
import com.example.data.model.room_hotel.RoomDto
import com.example.domain.model.room_hotel.RoomModel
import javax.inject.Inject

class RoomMapper @Inject constructor() : Mapper<RoomDto, RoomModel> {

    override fun mapFrom(from: RoomDto) = RoomModel(
        id = from.id,
        name = from.name,
        price = from.price,
        pricePer = from.pricePer,
        peculiarities = from.peculiarities,
        imageUrls = from.imageUrls
    )
}