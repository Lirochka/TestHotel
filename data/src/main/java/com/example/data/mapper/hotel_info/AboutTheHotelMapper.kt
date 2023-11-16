package com.example.data.mapper.hotel_info

import com.example.data.mapper.Mapper
import com.example.data.model.hotel_info.AboutTheHotelDto
import com.example.domain.model.hotel_info.AboutTheHotelModel
import javax.inject.Inject

class AboutTheHotelMapper @Inject constructor()
    : Mapper<AboutTheHotelDto, AboutTheHotelModel> {

    override fun mapFrom(from: AboutTheHotelDto) = AboutTheHotelModel(
        description = from.description,
        peculiarities = from.peculiarities
    )
}