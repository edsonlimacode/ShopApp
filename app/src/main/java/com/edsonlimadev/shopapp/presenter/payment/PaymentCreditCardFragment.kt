package com.edsonlimadev.shopapp.presenter.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentPaymentCreditCardBinding
import com.edsonlimadev.shopapp.presenter.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentCreditCardFragment : Fragment() {

    private lateinit var binding: FragmentPaymentCreditCardBinding

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPaymentCreditCardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListeners()
        initSpinner()
    }

    private fun initSpinner() {
        binding.spinner.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.installments,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )
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
        binding.btnCreditFinish.setOnClickListener {
            cartViewModel.clearCart(1)
            findNavController().navigate(R.id.action_global_finishFragment)
        }


        binding.editCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentText = s.toString()
                if (currentText.isNotEmpty()) {
                    binding.textNumber.text = currentText
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editCardDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentText = s.toString()
                if (currentText.isNotEmpty()) {
                    binding.textCardDate.text = currentText
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editCardName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentText = s.toString()
                if (currentText.isNotEmpty()) {
                    binding.textCardName.text = currentText
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editCardCvv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val currentText = s.toString()
                if (currentText.isNotEmpty()) {
                    binding.textCardCvv.text = currentText
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}