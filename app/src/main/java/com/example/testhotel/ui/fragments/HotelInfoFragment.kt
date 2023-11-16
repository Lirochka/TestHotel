package com.example.testhotel.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.models.SlideModel
import com.example.testhotel.common.formatPrice
import com.example.testhotel.databinding.FragmentHotelInfoBinding
import com.example.testhotel.ui.viewmodel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotelInfoFragment : Fragment() {

    private val viewModel: HotelViewModel by activityViewModels()

    private var _binding: FragmentHotelInfoBinding? = null
    private val binding: FragmentHotelInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelInfoBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHotelInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getDetailInfo()
        }

        viewModel.hotelInfoResult.observe(viewLifecycleOwner, Observer {
            with(binding) {
                val slideModels = it.imageUrls?.map { url ->
                    SlideModel(url)
                } ?: emptyList()

                hotelInfoImageSlider.setImageList(slideModels)
                hotelInfoRatingName.text = it.ratingName
                hotelInfoRating.text = it.rating.toString()
                hotelInfoName.text = it.name
                hotelInfoAdress.text = it.adress
                hotelInfoMinimalPrice.text = "от ${it.minimalPrice?.let { it1 -> formatPrice(it1) }} ₽"
                hotelInfoPriceForIt.text = it.priceForIt
                hotelInfoPeculiarities1.text = it.aboutHotel?.peculiarities?.getOrNull(0) ?: ""
                hotelInfoPeculiarities2.text = it.aboutHotel?.peculiarities?.getOrNull(1) ?: ""
                hotelInfoPeculiarities3.text = it.aboutHotel?.peculiarities?.getOrNull(2) ?: ""
                hotelInfoPeculiarities4.text = it.aboutHotel?.peculiarities?.getOrNull(3) ?: ""
                hotelInfoButton.setOnClickListener {
                    val name = hotelInfoName.text.toString()
                    launchHotelRoomFragment(name)
                }
            }
        })
    }

    private fun launchHotelRoomFragment(name: String) {
        findNavController().navigate(HotelInfoFragmentDirections.actionHotelInfoFragmentToHotelRoomFragment(name))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = HotelInfoFragment()
    }
}