package com.edsonlimadev.shopapp.presenter.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentAddressBinding


class AddressFragment : Fragment() {

    private lateinit var binding: FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddressBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListeners()

    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.tbAddress)
        (activity as AppCompatActivity).supportActionBar?.title = "Address"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbAddress.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initListeners() {
        binding.btnPaymentType.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_paymentTypeFragment)
        }
    }

}