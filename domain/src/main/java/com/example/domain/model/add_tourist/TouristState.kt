package com.example.domain.model.add_tourist

data class TouristState(
    val isNameValid: Boolean = true,
    val isSurnameValid: Boolean = true,
    val isDateOfBirthValid: Boolean = true,
    val isCitizenshipValid: Boolean = true,
    val isNumberPassportValid: Boolean = true,
    val isValidityPeriodValid: Boolean = true
)
