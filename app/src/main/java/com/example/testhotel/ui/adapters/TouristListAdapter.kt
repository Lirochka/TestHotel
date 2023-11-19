package com.example.testhotel.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.add_tourist.Tourist
import com.example.testhotel.R
import com.example.testhotel.common.DateUtils
import com.example.testhotel.databinding.ItemAddTouristBinding
import com.example.testhotel.ui.viewmodel.AddTouristViewModel

class TouristListAdapter(
    private val viewModel: AddTouristViewModel,
    private val lifecycleOwnerAdapter: LifecycleOwner,
    private val fragment: Fragment,
) : ListAdapter<Tourist, InfoTouristItemViewHolder>(TouristItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoTouristItemViewHolder {
        val binding = ItemAddTouristBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InfoTouristItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: InfoTouristItemViewHolder, position: Int) {
        val touristItem = getItem(position)
        val binding = holder.binding
        binding.apply {
            with(touristItem) {
                when (position) {
                    0 -> itemAddTitleTourist.text = "Второй турист"
                    1 -> itemAddTitleTourist.text = "Третий турист"
                    else -> itemAddTitleTourist.text = "Четвертый турист"
                }

                adapterViewModel = viewModel
                lifecycleOwner = lifecycleOwnerAdapter

                buttonAddTouristVisible.setOnClickListener {
                    if (itemAddCardInfoTourist.visibility == View.VISIBLE) {

                        itemAddCardInfoTourist.visibility = View.GONE
                        buttonAddTouristGone.visibility = View.VISIBLE
                        buttonAddTouristVisible.visibility = View.GONE
                    } else {
                        itemAddCardInfoTourist.visibility = View.VISIBLE
                        buttonAddTouristGone.visibility = View.GONE
                        buttonAddTouristVisible.visibility = View.VISIBLE
                    }
                }
                buttonAddTouristGone.setOnClickListener {
                    if (itemAddCardInfoTourist.visibility == View.GONE) {

                        itemAddCardInfoTourist.visibility = View.VISIBLE
                        buttonAddTouristGone.visibility = View.GONE
                        buttonAddTouristVisible.visibility = View.VISIBLE
                    } else {
                        itemAddCardInfoTourist.visibility = View.GONE
                        buttonAddTouristGone.visibility = View.VISIBLE
                        buttonAddTouristVisible.visibility = View.GONE
                    }
                }
            }
        }
        addTextChangeListeners(binding)
        setupDatePickerAdapter(binding)
        onFocusChangeListener(binding)
    }
    private fun addTextChangeListeners(binding: ItemAddTouristBinding) {
        binding.itemAddEditTextTouristName.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputName()
        })

        binding.itemAddEditTextTouristSurname.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputSurname()
        })

        binding.itemAddEditTextTouristDate.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputDateOfBirth()
        })

        binding.itemAddEditTextTouristCitizenship.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputCitizenship()
        })

        binding.itemAddEditTextNumberPassport.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputNumberPassport()
        })

        binding.itemAddEditTextValidityPeriod.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputValidityPeriod()
        })
    }

    private inline fun createTextWatcher(crossinline onTextChanged: (CharSequence) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onTextChanged(p0 ?: "")
            }

            override fun afterTextChanged(p0: Editable?) {}
        }
    }

    private fun onFocusChangeListener(binding: ItemAddTouristBinding) {
        binding.itemAddEditTextTouristName.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnNameField()
                }
            }

        binding.itemAddEditTextTouristSurname.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnSurNameField()
                }
            }

        binding.itemAddEditTextTouristDate.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnDataField()
                }
            }

        binding.itemAddEditTextTouristCitizenship.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedCitizenshipField()
                }
            }
        binding.itemAddEditTextNumberPassport.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedNumberField()
                }
            }

        binding.itemAddEditTextValidityPeriod.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedPeriodField()
                }
            }
    }

    fun addTourist(newTourist: Tourist) {
        val currentList = currentList.toMutableList()
        currentList.add(newTourist)
        submitList(currentList)
    }
    private fun setupDatePickerAdapter(binding: ItemAddTouristBinding) {

        val dateUtils = DateUtils(fragment)

        val editTextTouristDate = binding.itemAddEditTextTouristDate
        val editTextValidityPeriod = binding.itemAddEditTextValidityPeriod

        editTextTouristDate.setOnClickListener {
            dateUtils.attachToEditText(
                R.id.booking_editText_tourist_date
            )
            { formattedDate -> editTextTouristDate.setText(formattedDate) }
        }

        editTextValidityPeriod.setOnClickListener {
            dateUtils.attachToEditText(
                R.id.booking_editText_validity_period
            )
            { formattedDate -> editTextValidityPeriod.setText(formattedDate) }
        }
    }
}
