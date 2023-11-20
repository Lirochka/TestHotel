package com.example.testhotel.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.add_tourist.Tourist
import com.example.testhotel.ui.adapters.TouristListAdapter
import com.example.testhotel.R
import com.example.testhotel.common.DateUtils
import com.example.testhotel.common.formatPrice
import com.example.testhotel.databinding.FragmentBookingBinding
import com.example.testhotel.ui.viewmodel.AddTouristViewModel
import com.example.testhotel.ui.viewmodel.BookingFragmentViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookingFragment : Fragment() {
    private val viewModel: BookingFragmentViewModel by activityViewModels()
    private val addTouristViewModel: AddTouristViewModel by activityViewModels()

    private lateinit var touristListAdapter: TouristListAdapter
    private var allFieldsValid = false

    private lateinit var topAppBar: MaterialToolbar

    private var _binding: FragmentBookingBinding? = null
    private val binding: FragmentBookingBinding
        get() = _binding ?: throw RuntimeException("FragmentBookingBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModels()

        lifecycleScope.launch {
            viewModel.getBooking()
        }

        viewModel.bookingResult.observe(viewLifecycleOwner, Observer {
            with(binding) {
                bookingRating.text = it.horating.toString()
                bookingRatingName.text = it.ratingName
                bookingHotelName.text = it.hotelName
                bookingHotelAdress.text = it.hotelAdress
                bookingDeparture.text = it.departure
                bookingToCountry.text = it.arrivalCountry
                bookingDate.text = it.tourDateStart + " - " + it.tourDateStop
                bookingNumberOfNights.text = it.numberOfNights.toString()
                bookingHotel.text = it.hotelName
                bookingHotelRoom.text = it.room
                bookingNutrition.text = it.nutrition
                bookingTourPrice.text = "${it.tourPrice?.let { it1 -> formatPrice(it1) }} ₽"
                bookingFuelCharge.text = "${it.fuelCharge?.let { it1 -> formatPrice(it1) }} ₽"
                bookingServiceCharge.text =
                    "${it.serviceCharge?.let { it1 -> formatPrice(it1) }} ₽"

                val check = (it.tourPrice?.plus(it.fuelCharge!!)?.plus(it.serviceCharge!!))
                bookingToPaid.text = "${check?.let { it1 -> formatPrice(it1) }} ₽"
                bookingButton.text = "Оплатить ${check?.let { it1 -> formatPrice(it1) }} ₽"
            }
        })

        addTouristViewModel.touristList.observe(viewLifecycleOwner) { touristList ->
            touristListAdapter.submitList(touristList)
        }

        initTopAppBar()

        initAdapter()
        setupEmailValidation()
        phoneTextInput()
        setupDatePicker()
        visibleInfoTourist()
        checkValidData()
        addNewTourist()
        isTouristCardVisible()
        addTextChangeListeners(binding)
        observeViewModel()
        navigateToPaidFragment()
        onFocusChangeListener()
        clickButtonPay()
    }

    private fun initViewModels() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initTopAppBar() {
        topAppBar = binding.bookingTopAppBar
        topAppBar.setNavigationOnClickListener {
            val name = binding.bookingHotel.text.toString()
            navigateToHotelRoomFragment(name)
        }
    }

    private fun initAdapter() {
        val recyclerView = binding.bookingRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        touristListAdapter = TouristListAdapter(
            addTouristViewModel,
            viewLifecycleOwner,
            this
        )
        recyclerView.adapter = touristListAdapter
    }

    private fun navigateToHotelRoomFragment(name: String) {
        findNavController().navigate(
            BookingFragmentDirections.actionBookingFragmentToHotelRoomFragment(name)
        )
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner, Observer {
            navigateToPaidFragment()

        })

        viewModel.errorPhone.observe(viewLifecycleOwner, Observer { errorPhone ->
            binding.phoneTextInputLayout.error = errorPhone
        })

        viewModel.emailError.observe(viewLifecycleOwner, Observer { emailError ->
            binding.emailTextInputLayout.error = emailError
        })
    }

    private fun navigateToPaidFragment() {
        if (allFieldsValid) {
            findNavController().navigate(R.id.action_bookingFragment_to_paidFragment)
        }
    }

    private fun clickButtonPay() {
        binding.bookingButton.setOnClickListener {
            viewModel.validatePhoneNumber(binding.phoneTextInputEditText.text.toString())
            viewModel.validateEmail(binding.emailTextInputEditText.text.toString())
            checkValidData()
        }
    }

    private fun checkValidData() {
        val bookingFragmentFieldsValid = viewModel.validateInput(
            binding.bookingEditTextTouristName.text?.toString() ?: "",
            binding.bookingEditTextTouristSurname.text?.toString() ?: "",
            binding.bookingEditTextTouristDate.text?.toString() ?: "",
            binding.bookingEditTextTouristCitizenship.text?.toString() ?: "",
            binding.bookingEditTextNumberPassport.text?.toString() ?: "",
            binding.bookingEditTextValidityPeriod.text?.toString() ?: "",
        )

        val isPhoneValid = viewModel.errorPhone.value == null
        val isEmailValid = viewModel.emailError.value == null
        val isNameValid = addTouristViewModel.errorInputName.value == false
        val isSurnameValid = addTouristViewModel.errorInputSurname.value == false
        val isDateValid = addTouristViewModel.errorInputDateOfBirth.value == false
        val isCitizenshipValid = addTouristViewModel.errorInputCitizenship.value == false
        val isNumberPassportValid = addTouristViewModel.errorInputNumberPassport.value == false
        val isPeriodValid = addTouristViewModel.errorInputValidityPeriod.value == false


        allFieldsValid = bookingFragmentFieldsValid &&
                isPhoneValid &&
                isEmailValid &&
                isNameValid &&
                isSurnameValid &&
                isDateValid &&
                isCitizenshipValid &&
                isNumberPassportValid &&
                isPeriodValid

        if (allFieldsValid) {
            navigateToPaidFragment()
        }
    }
    private fun isTouristCardVisible() {
        viewModel.isTouristCardVisible.observe(viewLifecycleOwner) { isVisible ->
            if (isVisible) {
                binding.bookingCardAnotherTourist.visibility = View.VISIBLE
                binding.bookingRecyclerView.visibility = View.VISIBLE
            } else {
                binding.bookingCardAnotherTourist.visibility = View.GONE
                binding.bookingRecyclerView.visibility = View.GONE
            }
        }
    }
    private fun onFocusChangeListener() {
        binding.bookingEditTextTouristName.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnNameField()
                }
            }

        binding.bookingEditTextTouristSurname.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnSurNameField()
                }
            }

        binding.bookingEditTextTouristDate.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedOnDataField()
                }
            }

        binding.bookingEditTextTouristCitizenship.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedCitizenshipField()
                }
            }
        binding.bookingEditTextNumberPassport.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedNumberField()
                }
            }

        binding.bookingEditTextValidityPeriod.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    viewModel.userClickedPeriodField()
                }
            }
    }

    private fun addTextChangeListeners(binding: FragmentBookingBinding) {
        binding.bookingEditTextTouristName.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputName()
        })

        binding.bookingEditTextTouristSurname.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputSurname()
        })

        binding.bookingEditTextTouristDate.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputDateOfBirth()
        })

        binding.bookingEditTextTouristCitizenship.addTextChangedListener(createTextWatcher {

            viewModel.resetErrorInputCitizenship()
        })

        binding.bookingEditTextNumberPassport.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputNumberPassport()

        })

        binding.bookingEditTextValidityPeriod.addTextChangedListener(createTextWatcher {
            viewModel.resetErrorInputValidityPeriod()
        })
    }
    private inline fun createTextWatcher(crossinline onTextChanged: () -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onTextChanged()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }
    }
    private fun addNewTourist() {
        binding.bookingAddTouristButton.setOnClickListener {
            val newTourist = Tourist(
                title = "",
                name = "",
                surname = "",
                dateOfBirth = "",
                citizenship = "",
                numberPassport = "",
                validityPeriod = ""
            )
            touristListAdapter.addTourist(newTourist)
            addTouristViewModel.validateInput(
                newTourist.name,
                newTourist.surname,
                newTourist.dateOfBirth,
                newTourist.citizenship,
                newTourist.numberPassport,
                newTourist.validityPeriod
            )
            addTouristViewModel.addTourist( newTourist)
            viewModel.updateTouristCardVisibility(true)
        }
    }
    private fun visibleInfoTourist() {
        val cardInfoTourist = binding.bookingCardInfoTourist
        val buttonVisibleInfoTourist = binding.bookingButtonTouristVisible
        val buttonGoneInfoTourist = binding.bookingButtonTouristGone

        buttonVisibleInfoTourist.setOnClickListener {
            if (cardInfoTourist.visibility == View.VISIBLE) {

                cardInfoTourist.visibility = View.GONE
                buttonGoneInfoTourist.visibility = View.VISIBLE
                buttonVisibleInfoTourist.visibility = View.GONE
            } else {
                cardInfoTourist.visibility = View.VISIBLE
                buttonGoneInfoTourist.visibility = View.GONE
                buttonVisibleInfoTourist.visibility = View.VISIBLE
            }
        }
        buttonGoneInfoTourist.setOnClickListener {
            if (cardInfoTourist.visibility == View.GONE) {

                cardInfoTourist.visibility = View.VISIBLE
                buttonGoneInfoTourist.visibility = View.GONE
                buttonVisibleInfoTourist.visibility = View.VISIBLE
            } else {
                cardInfoTourist.visibility = View.GONE
                buttonGoneInfoTourist.visibility = View.VISIBLE
                buttonVisibleInfoTourist.visibility = View.GONE
            }
        }
    }
    private fun setupDatePicker() {

        val dateUtils = DateUtils(this)

        val editTextTouristDate = binding.bookingEditTextTouristDate
        val editTextValidityPeriod = binding.bookingEditTextValidityPeriod

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
    private fun phoneTextInput() {
        val phoneTextInputEditText = binding.phoneTextInputEditText

        phoneTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val phone = s.toString()
                viewModel.validatePhoneNumber(phone)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    private fun setupEmailValidation() {
        val emailTextInputEditText = binding.emailTextInputEditText

        emailTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                viewModel.validateEmail(email)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object {
        fun newInstance() = BookingFragment()
    }
}