package com.edsonlimadev.shopapp.presenter.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.edsonlimadev.shopapp.MainGraphDirections
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentPaymentPixBinding
import com.edsonlimadev.shopapp.presenter.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentPixFragment : Fragment() {

    private lateinit var binding: FragmentPaymentPixBinding

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentPixBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListeners()
    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.tbPaymentPix)
        (activity as AppCompatActivity).supportActionBar?.title = "Payment Pix"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbPaymentPix.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initListeners() {
        binding.btnFinishPix.setOnClickListener {

            cartViewModel.clearCart(1)

            val navOptions: NavOptions =
                NavOptions.Builder().setPopUpTo(R.id.cartFragment, true).build()

            findNavController().navigate(
                MainGraphDirections.actionGlobalFinishFragment(),
                navOptions
            )

        }

        binding.btnCopy.setOnClickListener {
            Toast.makeText(requireContext(), "Copied", Toast.LENGTH_LONG).show()
        }
    }
}