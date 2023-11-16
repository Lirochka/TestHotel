package com.example.domain.model.hotel_info

data class HotelModel(
    val aboutHotel: AboutTheHotelModel? = null,
    val adress: String? = null,
    val id: Int? = null,
    val imageUrls: List<String>? = null,
    val minimalPrice: Int? = null,
    val name: String? = null,
    val priceForIt: String? = null,
    val rating: Int? = null,
    val ratingName: String? = null,
)