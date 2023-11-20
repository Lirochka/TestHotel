package com.example.testhotel.ui.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.booking.BookingModel
import com.example.domain.use_case.GetBookingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltViewModel
class BookingFragmentViewModel @Inject constructor(
    private val getBookingUseCase: GetBookingUseCase,
) : MainViewModel() {

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