package com.example.data.repository

import com.example.data.mapper.booking.BookingMapper
import com.example.data.mapper.hotel_info.HotelMapper
import com.example.data.mapper.room_hotel.RoomsMapper
import com.example.data.model.booking.BookingDto
import com.example.data.model.hotel_info.HotelDto
import com.example.data.model.room_hotel.RoomsDto
import com.example.data.network.ApiService
import com.example.domain.model.booking.BookingModel
import com.example.domain.model.hotel_info.HotelModel
import com.example.domain.model.room_hotel.RoomsModel
import com.example.domain.repository.HotelRepository
import java.net.SocketTimeoutException
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val hotelMapper: HotelMapper,
    private val roomsMapper: RoomsMapper,
    private val bookingMapper: BookingMapper
) : HotelRepository {

    override suspend fun getHotelInfo(): HotelModel {
        var hotelInfo : HotelDto? = null
        try {
            hotelInfo = apiService.getHotel()
        }
        catch (e: SocketTimeoutException) {
        }
        return hotelInfo?.let { hotelMapper.mapFrom(it) } ?: HotelModel()
    }

    override suspend fun getListHotelRooms(): RoomsModel {
       var hotelRooms: RoomsDto? = null

        try {
            hotelRooms  = apiService.getHotelRooms()
        }
        catch (e: SocketTimeoutException) {
        }
        return hotelRooms?.let { roomsMapper.mapFrom(it) } ?: RoomsModel()
    }

    override suspend fun getBooking(): BookingModel {
        var booking: BookingDto? = null
        try {
            booking = apiService.getBooking()

        } catch (e: SocketTimeoutException) {
        }
        return booking?.let { bookingMapper.mapFrom(it) } ?: BookingModel()
    }
}
