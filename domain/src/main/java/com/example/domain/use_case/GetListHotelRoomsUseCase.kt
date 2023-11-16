package com.example.domain.use_case

import com.example.domain.repository.HotelRepository
import javax.inject.Inject

class GetListHotelRoomsUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    suspend operator fun invoke() = repository.getListHotelRooms()
}