package com.example.domain.model.booking

data class BookingModel(
    val arrivalCountry: String? = null,
    val departure: String? = null,
    val fuelCharge: Int? = null,
    val horating: Int? = null,
    val hotelAdress: String? = null,
    val hotelName: String? = null,
    val id: Int? = null,
    val numberOfNights: Int? = null,
    val nutrition: String? = null,
    val ratingName: String? = null,
    val room: String? = null,
    val serviceCharge: Int? = null,
    val tourDateStart: String? = null,
    val tourDateStop: String? = null,
    val tourPrice: Int? = null
)