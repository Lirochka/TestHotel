package com.example.data.model.hotel_info

import com.google.gson.annotations.SerializedName

data class AboutTheHotelDto(
    @SerializedName("description") val description: String? = null,
    @SerializedName("peculiarities") val peculiarities: List<String>? = null
)