package com.example.data.mapper.booking

import com.example.data.mapper.Mapper
import com.example.data.model.booking.BookingDto
import com.example.domain.model.booking.BookingModel
import javax.inject.Inject

class BookingMapper @Inject constructor() : Mapper<BookingDto, BookingModel> {

    override fun mapFrom(from: BookingDto) = BookingModel(
        arrivalCountry = from.arrivalCountry,
        departure = from.departure,
        fuelCharge = from.fuelCharge,
        horating = from.horating,
        hotelAdress = from.hotelAdress,
        hotelName = from.hotelName,
        id = from.id,
        numberOfNights = from.numberOfNights,
        nutrition = from.nutrition,
        ratingName = from.ratingName,
        room = from.room,
        serviceCharge = from.serviceCharge,
        tourDateStart = from.tourDateStart,
        tourDateStop = from.tourDateStop,
        tourPrice = from.tourPrice
    )
}