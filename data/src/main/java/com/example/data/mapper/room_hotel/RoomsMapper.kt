package com.example.data.mapper.room_hotel

import com.example.data.mapper.Mapper
import com.example.data.model.room_hotel.RoomsDto
import com.example.domain.model.room_hotel.RoomsModel
import javax.inject.Inject

class RoomsMapper @Inject constructor(
    private val roomMapper: RoomMapper,
) : Mapper<RoomsDto, RoomsModel> {

    override fun mapFrom(from: RoomsDto) = RoomsModel(
        roomModels = ArrayList(from.roomsDto.map {
            roomMapper.mapFrom(it)
        })
    )
}