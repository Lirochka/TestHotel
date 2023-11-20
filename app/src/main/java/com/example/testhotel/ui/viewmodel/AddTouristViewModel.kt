package com.example.testhotel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.add_tourist.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTouristViewModel @Inject constructor()
    : MainViewModel() {

    private val _touristList = MutableLiveData<List<Tourist>>()
    val touristList: LiveData<List<Tourist>>
        get() = _touristList

    fun addTourist(newTourist: Tourist) {
        val currentList = (_touristList.value ?: emptyList()).toMutableList()
        currentList.add(newTourist)
        _touristList.value = currentList
    }
}