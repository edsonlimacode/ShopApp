package com.edsonlimadev.shopapp.presenter.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentCartBinding
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartAdapter: CartAdapter

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initRecycler()
        getAllCart()
        initCartObserver()
        initListeners()
    }

    private fun initListeners() {

        binding.btnFinishCart.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_addressFragment)
        }
    }

    private fun initRecycler() {
        cartAdapter = CartAdapter(
            onRemoveProduct = {
                cartViewModel.deleteProductById(it.userId, it.productId)

            },
            onAddItem = {
                cartViewModel.addQuantityProduct(it.userId, it.productId)
            },
            onRemoveItem = {
                cartViewModel.removeQuantityProduct(it.userId, it.productId)
            }
        )
        binding.rvCart.adapter = cartAdapter
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.tbCart)
        (activity as AppCompatActivity).supportActionBar?.title = "Cart"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbCart.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getAllCart() {
        cartViewModel.getAllItems(1)
    }

    private fun initCartObserver() {
        cartViewModel.products.observe(viewLifecycleOwner) { stateUi ->
            when (stateUi) {
                is StateUi.Loading -> {}
                is StateUi.Success -> {
                    if (stateUi.data?.isEmpty() == true) {
                        binding.btnFinishCart.isVisible = false
                        binding.textView2.isVisible = false
                    }

                    cartAdapter.submitList(stateUi.data)
                    val total = stateUi.data?.sumOf { it?.price?.times(it.quantity) ?: 0.0 }
                    binding.textView2.text = "Total: R$ ${String.format("%.2f", total)}"

                }

                is StateUi.Error -> {
                    Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        cartViewModel.unit.observe(viewLifecycleOwner) { stateUi ->
            when (stateUi) {
                is StateUi.Loading -> {}
                is StateUi.Success -> {}
                is StateUi.Error -> {
                    Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}