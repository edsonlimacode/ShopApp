package com.edsonlimadev.shopapp.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.FragmentHomeBinding
import com.edsonlimadev.shopapp.domain.model.Product
import com.edsonlimadev.shopapp.presenter.cart.CartViewModel
import com.edsonlimadev.shopapp.presenter.category.CategoryAdapter
import com.edsonlimadev.shopapp.presenter.category.CategoryViewModel
import com.edsonlimadev.shopapp.presenter.product.ProductAdapter
import com.edsonlimadev.shopapp.presenter.product.ProductViewModel
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val categoryViewModel: CategoryViewModel by viewModels()

    private val productViewModel: ProductViewModel by viewModels()

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var productAdapter: ProductAdapter

    private lateinit var productsList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryViewModel.getAllCategories()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel.getProducts()

        initCategoryRecycler()
        initProductRecycler()
        initSearch()
        initObservers()
        initListeners()
        countCartItens()
    }

    private fun initListeners() {
        binding.btnOpenMenu.setOnClickListener {
            homeViewModel.onHomeButtonClicked()
        }

        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_cartFragment)
        }
    }

    private fun initCategoryRecycler() {
        categoryAdapter = CategoryAdapter(requireContext()) { category ->
            productViewModel.getProductsByCategoryName(category)
            productByCategoryObserver()
        }

        binding.rvCategories.adapter = categoryAdapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initProductRecycler() {

        productAdapter = ProductAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(it)
            findNavController().navigate(action)
        }

        binding.rvProducts.adapter = productAdapter

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    private fun initObservers() {

        lifecycleScope.launch {
            categoryViewModel.categoryList.collectLatest { stateUi ->
                when (stateUi) {
                    is StateUi.Loading -> {
                        binding.pbCategories.isVisible = true
                    }

                    is StateUi.Success -> {
                        stateUi.data?.let {
                            binding.pbCategories.isVisible = false
                            categoryAdapter.updateCategories(stateUi.data)
                        }
                    }

                    is StateUi.Error -> {
                        binding.pbCategories.isVisible = false
                        Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            productViewModel.products.collectLatest { stateUi ->
                when (stateUi) {
                    is StateUi.Loading -> {
                        binding.pbProducts.isVisible = true
                    }

                    is StateUi.Success -> {
                        stateUi.data?.let {
                            productsList = stateUi.data
                            binding.pbProducts.isVisible = false
                            productAdapter.submitList(productsList)
                        }
                    }

                    is StateUi.Error -> {
                        binding.pbProducts.isVisible = false
                        Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

        lifecycleScope.launch {
            cartViewModel.count.observe(viewLifecycleOwner) { stateUi ->
                when (stateUi) {
                    is StateUi.Loading -> {}
                    is StateUi.Success -> {
                        stateUi.data?.let {
                            if (it == 0) {
                                binding.textNotification.isVisible = false
                            } else {
                                binding.textNotification.isVisible = true
                                binding.textNotification.text = it.toString()
                            }
                        }
                    }

                    is StateUi.Error -> {
                        Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }

    private fun productByCategoryObserver() {
        lifecycleScope.launch {
            productViewModel.productsByCategory.collectLatest { stateUi ->
                when (stateUi) {
                    is StateUi.Loading -> {
                        binding.pbProducts.isVisible = true
                        binding.rvProducts.isVisible = false
                    }

                    is StateUi.Success -> {
                        stateUi.data?.let {
                            productsList = stateUi.data
                            binding.pbProducts.isVisible = false
                            productAdapter.submitList(stateUi.data)
                            binding.rvProducts.isVisible = true
                        }
                    }

                    is StateUi.Error -> {
                        binding.pbProducts.isVisible = false
                        binding.rvProducts.isVisible = true
                        Toast.makeText(requireContext(), stateUi.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun initSearch() {

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val newList =
                        productsList.filter { it.title.contains(newText.toString(), true) }
                    productAdapter.submitList(newList)
                } else {
                    productAdapter.submitList(productsList)
                }
                return true
            }

        })
    }

    private fun countCartItens() {
        cartViewModel.countCart(1)
    }

}