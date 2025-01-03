package com.edsonlimadev.shopapp.presenter.product.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.edsonlimadev.shopapp.databinding.FragmentProductDetailsBinding
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.model.Product
import com.edsonlimadev.shopapp.presenter.cart.CartViewModel
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding

    private val args: ProductDetailsFragmentArgs by navArgs()

    private val productViewModel: ProductDetailsViewModel by viewModels()

    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        getProductById()

        getProductByIdObserver()
        addToFavoriteObserver()
        addProductToCart()
    }

    private fun initListeners() {

        binding.btnAddToFavorite.setOnClickListener {
            addToFavorite()
        }

        binding.btnAddToCart.setOnClickListener {
            cartViewModel.getProductById(1, product.id)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getProductById() {
        productViewModel.getProductById(args.productId)
    }

    private fun getProductByIdObserver() = lifecycleScope.launch {
        productViewModel.products.collectLatest { stateUi ->
            when (stateUi) {
                is StateUi.Loading -> {
                    binding.pbProductDetails.isVisible = true
                    binding.clDetails.isVisible = false
                }

                is StateUi.Success -> {
                    binding.pbProductDetails.isVisible = false
                    binding.clDetails.isVisible = true
                    stateUi.data?.let {
                        product = it
                        setProductData(it)
                    }
                }

                is StateUi.Error -> {
                    binding.clDetails.isVisible = true
                    binding.pbProductDetails.isVisible = false
                    Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setProductData(product: Product) {
        Glide.with(binding.root.context).load(product.image).into(binding.imgProductDetail)
        binding.textProductTitle.text = product.title
        binding.textProductPrice.text = product.price
        binding.ratingProductQuantity.setText(product.rating.rate.toString())
        binding.textProductQuantity.text = "Qnt. ${product.rating.count}"
        binding.textProductDescription.text = product.description
    }

    private fun addToFavoriteObserver() {
        lifecycleScope.launch {
            productViewModel.favorite.collectLatest { stateUi ->
                when (stateUi) {
                    is StateUi.Loading -> {}
                    is StateUi.Success -> {
                        Toast.makeText(
                            requireContext(),
                            "Product added to favorite",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is StateUi.Error -> {
                        Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun addToFavorite() {

        val favoriteLocal = FavoriteLocal(
            id = product.id,
            userId = 1,
            productId = product.id,
            title = product.title,
            price = product.price.toDouble(),
            image = product.image
        )

        lifecycleScope.launch(Dispatchers.IO) {
            productViewModel.insertToFavorite(favoriteLocal)
        }
    }

    private fun addProductToCart() {

        cartViewModel.product.observe(viewLifecycleOwner) { cartUiState ->

            when (cartUiState) {
                is StateUi.Loading -> {}
                is StateUi.Success -> {
                    if (cartUiState.data != null) {
                        cartViewModel.addQuantityProduct(1, product.id)
                    } else {
                        val productLocal = ProductLocal(
                            userId = 1,
                            productId = product.id,
                            title = product.title,
                            price = product.price.toDouble(),
                            image = product.image,
                        )
                        Toast.makeText(
                            requireContext(),
                            "Product added to cart",
                            Toast.LENGTH_SHORT
                        ).show()
                        cartViewModel.addToCart(productLocal)
                    }
                }

                is StateUi.Error -> {
                    Toast.makeText(requireContext(), cartUiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}