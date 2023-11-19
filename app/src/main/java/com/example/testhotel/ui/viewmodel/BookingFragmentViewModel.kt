package com.example.testhotel.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.booking.BookingModel
import com.example.domain.use_case.GetBookingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltViewModel
class BookingFragmentViewModel @Inject constructor(
    private val getBookingUseCase: GetBookingUseCase,
) : ViewModel() {

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

    private val _shouldCloseScreen = MutableLiveData<Boolean>()
    val shouldCloseScreen: LiveData<Boolean>
        get() = _shouldCloseScreen

    private val _bookingResult = MutableLiveData<BookingModel>()
    val bookingResult: LiveData<BookingModel> = _bookingResult

    private val _errorPhone = MutableLiveData<String?>()
    val errorPhone: LiveData<String?>
        get() = _errorPhone

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?>
        get() = _emailError

    private val _isTouristCardVisible = MutableLiveData<Boolean>()
    val isTouristCardVisible: LiveData<Boolean>
        get() = _isTouristCardVisible
    fun updateTouristCardVisibility(isVisible: Boolean) {
        _isTouristCardVisible.value = isVisible
    }

    var email: String = ""
    var phone: String = ""
    var nameTourist: String = ""
    var surname: String = ""
    var dateOfBirth: String = ""
    var citizenship: String = ""
    var numberPassport: String = ""
    var validityPeriod: String = ""

    val emailFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            validateEmail(email)
        }
    }
    fun validateEmail(email: String) {
        if (email.isNullOrBlank() || !validateEmailFormat(email)) {
            _emailError.value = "Invalid email address"
        } else {
            _emailError.value = null
        }
    }
    private fun validateEmailFormat(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        return email.matches(emailPattern.toRegex())
    }
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

    val phoneMask = MutableLiveData<String>().apply {
        value = "+7([000])[000]-[00]-[00]"
    }
    fun validatePhoneNumber(phoneNumber: String) {
        if (isValidPhoneNumber(phoneNumber)) {
            _errorPhone.value = null
        } else {
            _errorPhone.value = "Invalid phone number"
        }
    }
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val pattern = "^(8|\\+?7)\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$"
        val regex = Regex(pattern)
        return regex.matches(phoneNumber)
    }
    suspend fun getBooking() {
        withContext(Dispatchers.IO) {
            val bookingApiResult = getBookingUseCase.invoke()
            _bookingResult.postValue(bookingApiResult)
        }
    }
}