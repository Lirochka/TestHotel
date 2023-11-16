package com.example.data.model.room_hotel

import com.google.gson.annotations.SerializedName


data class RoomDto (

  @SerializedName("id") var id: Int? = null,
  @SerializedName("name") var name: String? = null,
  @SerializedName("price") var price : Int? = null,
  @SerializedName("price_per") var pricePer : String? = null,
  @SerializedName("peculiarities") var peculiarities : ArrayList<String> = arrayListOf(),
  @SerializedName("image_urls") var imageUrls  : ArrayList<String> = arrayListOf()

)