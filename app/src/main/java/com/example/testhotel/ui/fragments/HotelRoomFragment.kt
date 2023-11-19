package com.example.testhotel.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testhotel.R
import com.example.testhotel.databinding.FragmentHotelRoomBinding
import com.example.testhotel.ui.adapters.RoomsListAdapter
import com.example.testhotel.ui.viewmodel.HotelViewModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotelRoomFragment : Fragment() {

    private val viewModel: HotelViewModel by activityViewModels()

    lateinit var topAppBar: MaterialToolbar
    private val args by navArgs<HotelRoomFragmentArgs>()

    private var _binding: FragmentHotelRoomBinding? = null
    private val binding: FragmentHotelRoomBinding
        get() = _binding ?: throw RuntimeException("FragmentHotelRoomBinding = null")

    private lateinit var roomsListAdapter: RoomsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHotelRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigate()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.roomRecyclerView.layoutManager = layoutManager
        roomsListAdapter = RoomsListAdapter()
        binding.roomRecyclerView.adapter = roomsListAdapter
        binding.roomRecyclerView.itemAnimator = null

        roomsListAdapter.onChooseRoomClickListener = object : RoomsListAdapter.OnChooseRoomClickListener{
            override fun onRoomClick() {
                findNavController().navigate(R.id.action_hotelRoomFragment_to_bookingFragment)
            }
        }

        lifecycleScope.launch {
            viewModel.loadRoomsList()
        }
        viewModel.roomsData.observe(viewLifecycleOwner, Observer { roomsModel ->
            roomsListAdapter.submitList(roomsModel.roomModels)

        })
    }

    private fun navigate(){
        topAppBar = binding.topAppBar
        args.hotel?.let { hotel ->
            topAppBar.title = hotel
        }
        topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_hotelRoomFragment_to_hotelInfoFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = HotelRoomFragment()
    }
}