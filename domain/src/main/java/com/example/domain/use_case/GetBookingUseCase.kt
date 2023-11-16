package com.example.domain.use_case

import com.example.domain.repository.HotelRepository
import javax.inject.Inject

class GetBookingUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    suspend operator fun invoke() = repository.getBooking()
}