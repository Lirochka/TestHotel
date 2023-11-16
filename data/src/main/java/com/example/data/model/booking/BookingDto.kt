package com.example.data.model.booking

import com.google.gson.annotations.SerializedName


data class BookingDto (
  @SerializedName("id") var id : Int? = null,
  @SerializedName("hotel_name") var hotelName : String? = null,
  @SerializedName("hotel_adress") var hotelAdress: String? = null,
  @SerializedName("horating") var horating: Int? = null,
  @SerializedName("rating_name") var ratingName: String? = null,
  @SerializedName("departure") var departure: String? = null,
  @SerializedName("arrival_country") var arrivalCountry: String? = null,
  @SerializedName("tour_date_start") var tourDateStart: String? = null,
  @SerializedName("tour_date_stop") var tourDateStop: String? = null,
  @SerializedName("number_of_nights") var numberOfNights: Int? = null,
  @SerializedName("room") var room: String? = null,
  @SerializedName("nutrition") var nutrition: String? = null,
  @SerializedName("tour_price") var tourPrice: Int? = null,
  @SerializedName("fuel_charge") var fuelCharge: Int? = null,
  @SerializedName("service_charge") var serviceCharge: Int? = null
)