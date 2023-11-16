package com.example.testhotel.common

import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class DateUtils(private val fragment: Fragment) {
    private var clickedEditTextId: Int = -1

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select Date")
        .build()

    init {
        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = Date(selection)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormat.format(calendar.time)

            handleFormattedDate(formattedDate) // Call the callback function here
        }
    }

    fun attachToEditText(editTextId: Int, callback: (String) -> Unit) {
        clickedEditTextId = editTextId
        handleFormattedDate = callback // Assign the callback function
        datePicker.show(fragment.childFragmentManager, "DATE_PICKER")
    }

    private lateinit var handleFormattedDate: (String) -> Unit
}
