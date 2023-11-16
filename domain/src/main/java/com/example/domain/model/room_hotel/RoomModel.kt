package com.example.domain.model.room_hotel

data class RoomModel (
  val id: Int? = null,
  val name: String? = null,
  val price : Int? = null,
  val pricePer : String? = null,
  val peculiarities : ArrayList<String> = arrayListOf(),
  val imageUrls  : ArrayList<String> = arrayListOf()

)