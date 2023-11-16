package com.example.data.mapper.hotel_info

import com.example.data.mapper.Mapper
import com.example.data.model.hotel_info.HotelDto
import com.example.domain.model.hotel_info.HotelModel
import javax.inject.Inject

class HotelMapper @Inject constructor(
    private val aboutTheHotelMapper: AboutTheHotelMapper
) : Mapper<HotelDto, HotelModel> {

    override fun mapFrom(from: HotelDto)= HotelModel(
        aboutHotel = from.aboutHotel?.let {
            aboutTheHotelMapper.mapFrom(it)
        },
        adress = from.adress,
        id = from.id,
        imageUrls = from.imageUrls,
        minimalPrice = from.minimalPrice,
        name = from.name,
        priceForIt = from.priceForIt,
        rating = from.rating,
        ratingName = from.ratingName
    )
}