package com.example.testhotel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MainViewModel: ViewModel() {

    private val _showNameError = MutableLiveData<Boolean>()
    val showNameError: LiveData<Boolean>
        get() = _showNameError

    private val _showSurnameError = MutableLiveData<Boolean>()
    val showSurnameError: LiveData<Boolean>
        get() = _showSurnameError

    private val _showDateOfBirthError = MutableLiveData<Boolean>()
    val showDateOfBirthError: LiveData<Boolean>
        get() = _showDateOfBirthError

    private val _showCitizenshipError = MutableLiveData<Boolean>()
    val showCitizenshipError: LiveData<Boolean>
        get() = _showCitizenshipError

    private val _showNumberPassportError = MutableLiveData<Boolean>()
    val showNumberPassportError: LiveData<Boolean>
        get() = _showNumberPassportError

    private val _showValidityPeriodError = MutableLiveData<Boolean>()
    val showValidityPeriodError: LiveData<Boolean>
        get() = _showValidityPeriodError

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputSurname = MutableLiveData<Boolean>()
    val errorInputSurname: LiveData<Boolean>
        get() = _errorInputSurname

    private val _errorInputDateOfBirth = MutableLiveData<Boolean>()
    val errorInputDateOfBirth: LiveData<Boolean>
        get() = _errorInputDateOfBirth

    private val _errorInputCitizenship = MutableLiveData<Boolean>()
    val errorInputCitizenship: LiveData<Boolean>
        get() = _errorInputCitizenship

    private val _errorInputNumberPassport = MutableLiveData<Boolean>()
    val errorInputNumberPassport: LiveData<Boolean>
        get() = _errorInputNumberPassport

    private val _errorInputValidityPeriod = MutableLiveData<Boolean>()
    val errorInputValidityPeriod: LiveData<Boolean>
        get() = _errorInputValidityPeriod

    var nameTourist: String = ""
    var surname: String = ""
    var dateOfBirth: String = ""
    var citizenship: String = ""
    var numberPassport: String = ""
    var validityPeriod: String = ""
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
            _errorInputName.value = true
            result = false
        }
        if (surname.isBlank()) {
            _errorInputSurname.value = true
            result = false
        }
        if (dateOfBirth.isBlank()) {
            _errorInputDateOfBirth.value = true
            result = false
        }
        if (citizenship.isBlank()) {
            _errorInputCitizenship.value = true
            result = false
        }
        if (numberPassport.isBlank()) {
            _errorInputNumberPassport.value = true
            result = false
        }
        if (validityPeriod.isBlank()) {
            _errorInputValidityPeriod.value = true
            result = false

        }
        return result
    }
    fun resetErrorInputName() {
        _errorInputName.postValue(false)
    }

    fun resetErrorInputSurname() {
        _errorInputSurname.postValue(false)
    }

    fun resetErrorInputDateOfBirth() {
        _errorInputDateOfBirth.postValue(false)
    }

    fun resetErrorInputNumberPassport() {
        _errorInputNumberPassport.postValue(false)
    }

    fun resetErrorInputValidityPeriod() {
        _errorInputValidityPeriod.postValue(false)
    }

    fun resetErrorInputCitizenship() {
        _errorInputCitizenship.postValue(false)
    }

    fun userClickedOnNameField() {
        _showNameError.value = true
    }

    fun userClickedOnSurNameField() {
        _showSurnameError.value = true
    }

    fun userClickedOnDataField() {
        _showDateOfBirthError.value = true
    }

    fun userClickedCitizenshipField() {
        _showCitizenshipError.value = true
    }

    fun userClickedNumberField() {
        _showNumberPassportError.value = true
    }

    fun userClickedPeriodField() {
        _showValidityPeriodError.value = true
    }
}