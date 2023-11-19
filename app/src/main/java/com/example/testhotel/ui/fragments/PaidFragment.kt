package com.example.testhotel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testhotel.R
import com.example.testhotel.databinding.FragmentPaidBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaidFragment : Fragment() {

    private var _binding: FragmentPaidBinding? = null
    private val binding: FragmentPaidBinding
        get() = _binding ?: throw RuntimeException("FragmentPaidBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val random = (104893..106898).random()
        val orderDetailsText = getString(R.string.order_confirmation_details_1) +
                " №$random " + getString(R.string.order_confirmation_details_2)
        binding.textOrderConfirmationDetails.text = orderDetailsText


        binding.paidButton.setOnClickListener {
            findNavController().navigate(R.id.action_paidFragment_to_hotelInfoFragment)
        }
        binding.topAppBarToBooking.setOnClickListener {
            findNavController().navigate(R.id.action_paidFragment_to_bookingFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PaidFragment().apply {
            }
    }
}