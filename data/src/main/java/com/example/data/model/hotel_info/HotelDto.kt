package com.example.data.model.hotel_info

import com.example.data.model.hotel_info.AboutTheHotelDto
import com.google.gson.annotations.SerializedName

data class HotelDto(
    @SerializedName("about_the_hotel") val aboutHotel: AboutTheHotelDto? = null,
    @SerializedName("adress") val adress: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("image_urls") val imageUrls: List<String>? = null,
    @SerializedName("minimal_price") val minimalPrice: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("price_for_it") val priceForIt: String? = null,
    @SerializedName("rating") val rating: Int? = null,
    @SerializedName("rating_name") val ratingName: String? = null
)