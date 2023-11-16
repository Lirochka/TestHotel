package com.example.testhotel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.hotel_info.HotelModel
import com.example.domain.model.room_hotel.RoomsModel
import com.example.domain.use_case.GetBookingUseCase
import com.example.domain.use_case.GetHotelInfoUseCase
import com.example.domain.use_case.GetListHotelRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelInfoUseCase: GetHotelInfoUseCase,
    private val getListHotelRoomsUseCase: GetListHotelRoomsUseCase,
    private val getBookingUseCase: GetBookingUseCase
) : ViewModel() {

    private val _hotelInfoResult: MutableLiveData<HotelModel> = MutableLiveData()
    val hotelInfoResult: LiveData<HotelModel> = _hotelInfoResult

    private val _roomsData = MutableLiveData<RoomsModel>()
    val roomsData: LiveData<RoomsModel> = _roomsData

    suspend fun loadRoomsList() {
        withContext(Dispatchers.IO){
            val roomsResult = getListHotelRoomsUseCase()
            _roomsData.postValue(roomsResult)
        }
    }

    suspend fun getDetailInfo() {
        withContext(Dispatchers.IO) {
            val hotelInfoApiResult = getHotelInfoUseCase.invoke()
            _hotelInfoResult.postValue(hotelInfoApiResult)
        }
    }
}