package com.edsonlimadev.shopapp.presenter.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentPaymentTypeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentTypeFragment : Fragment() {

    private lateinit var binding: FragmentPaymentTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentTypeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListeners()
    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.tbPaymentType)
        (activity as AppCompatActivity).supportActionBar?.title = "Payment Type"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbPaymentType.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun initListeners() {
        binding.cvCreditCard.setOnClickListener {
            findNavController().navigate(R.id.action_paymentTypeFragment_to_paymentCreditCardFragment)
        }

        binding.cvPix.setOnClickListener {
            findNavController().navigate(R.id.action_paymentTypeFragment_to_paymentPixFragment)
        }

    }
}