package com.example.data.network

import com.example.data.model.booking.BookingDto
import com.example.data.model.hotel_info.HotelDto
import com.example.data.model.room_hotel.RoomsDto
import retrofit2.http.GET

interface ApiService {

    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelDto?
    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getHotelRooms(): RoomsDto?

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooking(): BookingDto?
}