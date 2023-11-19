package com.example.testhotel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.add_tourist.Tourist
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTouristViewModel @Inject constructor() : ViewModel() {

    private val _showAddNameError = MutableLiveData<Boolean>()
    val showAddNameError: LiveData<Boolean>
        get() = _showAddNameError

    private val _showAddSurnameError = MutableLiveData<Boolean>()
    val showAddSurnameError: LiveData<Boolean>
        get() = _showAddSurnameError

    private val _showAddDateOfBirthError = MutableLiveData<Boolean>()
    val showAddDateError: LiveData<Boolean>
        get() = _showAddDateOfBirthError

    private val _showAddCitizenshipError = MutableLiveData<Boolean>()
    val showAddCitizenshipError: LiveData<Boolean>
        get() = _showAddCitizenshipError

    private val _showAddPassportError = MutableLiveData<Boolean>()
    val showAddPassportError: LiveData<Boolean>
        get() = _showAddPassportError

    private val _showAddValidPeriodError = MutableLiveData<Boolean>()
    val showAddValidPeriodError: LiveData<Boolean>
        get() = _showAddValidPeriodError

    private val _errorAddInputName = MutableLiveData<Boolean>()
    val errorAddInputName: LiveData<Boolean>
        get() = _errorAddInputName

    private val _errorAddInputSurname = MutableLiveData<Boolean>()
    val errorAddInputSurname: LiveData<Boolean>
        get() = _errorAddInputSurname

    private val _errorAddInputDate = MutableLiveData<Boolean>()
    val errorAddInputDate: LiveData<Boolean>
        get() = _errorAddInputDate

    private val _errorAddInputCitizenship = MutableLiveData<Boolean>()
    val errorAddInputCitizenship: LiveData<Boolean>
        get() = _errorAddInputCitizenship

    private val _errorAddInputPassport = MutableLiveData<Boolean>()
    val errorAddInputPassport: LiveData<Boolean>
        get() = _errorAddInputPassport

    private val _errorAddInputValidPeriod = MutableLiveData<Boolean>()
    val errorAddInputValidPeriod: LiveData<Boolean>
        get() = _errorAddInputValidPeriod

    private val _touristList = MutableLiveData<List<Tourist>>()
    val touristList: LiveData<List<Tourist>>
        get() = _touristList

    fun addTourist(newTourist: Tourist) {
        val currentList = (_touristList.value ?: emptyList()).toMutableList()
        currentList.add(newTourist)
        _touristList.value = currentList
    }

    var nameTouristAdd: String = ""
    var surnameAdd: String = ""
    var dateOfBirthAdd: String = ""
    var citizenshipAdd: String = ""
    var numberPassportAdd: String = ""
    var validityPeriodAdd: String = ""

    fun validateInput(
        name: String,
        surname: String,
        dateOfBirth: String,
        citizenship: String,
        numberPassport: String,
        validityPeriod: String,
    ): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorAddInputName.value = true
            result = false
        }
        if (surname.isBlank()) {
            _errorAddInputSurname.value = true
            result = false
        }
        if (dateOfBirth.isBlank()) {
            _errorAddInputDate.value = true
            result = false
        }
        if (citizenship.isBlank()) {
            _errorAddInputCitizenship.value = true
            result = false
        }
        if (numberPassport.isBlank()) {
            _errorAddInputPassport.value = true
            result = false
        }
        if (validityPeriod.isBlank()) {
            _errorAddInputValidPeriod.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorAddInputName.postValue(false)
    }

    fun resetErrorInputSurname() {
        _errorAddInputSurname.postValue(false)
    }

    fun resetErrorInputDateOfBirth() {
        _errorAddInputDate.postValue(false)
    }

    fun resetErrorInputNumberPassport() {
        _errorAddInputPassport.postValue(false)
    }

    fun resetErrorInputValidityPeriod() {
        _errorAddInputValidPeriod.postValue(false)
    }

    fun resetErrorInputCitizenship() {
        _errorAddInputCitizenship.postValue(false)
    }

    fun userClickedOnNameField() {
        _showAddNameError.value = true
    }

    fun userClickedOnSurNameField() {
        _showAddSurnameError.value = true
    }

    fun userClickedOnDataField() {
        _showAddDateOfBirthError.value = true
    }

    fun userClickedCitizenshipField() {
        _showAddCitizenshipError.value = true
    }

    fun userClickedNumberField() {
        _showAddPassportError.value = true
    }

    fun userClickedPeriodField() {
        _showAddValidPeriodError.value = true
    }
}