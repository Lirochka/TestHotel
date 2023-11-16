package com.example.domain.repository

import com.example.domain.model.booking.BookingModel
import com.example.domain.model.hotel_info.HotelModel
import com.example.domain.model.room_hotel.RoomsModel

interface HotelRepository {

 suspend fun getHotelInfo(): HotelModel

 suspend fun getListHotelRooms(): RoomsModel

 suspend fun getBooking(): BookingModel
}