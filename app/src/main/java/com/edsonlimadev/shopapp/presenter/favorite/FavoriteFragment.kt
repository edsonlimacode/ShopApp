package com.edsonlimadev.shopapp.presenter.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsonlimadev.shopapp.databinding.FragmentFavoriteBinding
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initRecycler()
        getAllFavorites()
        initFavoriteObserver()

    }

    private fun initRecycler() {
        favoriteAdapter = FavoriteAdapter {
            deleteFavorite(it)
        }
        binding.rvFavorite.adapter = favoriteAdapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.tbFavorite)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbFavorite.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun getAllFavorites() {
        lifecycleScope.launch {
            favoriteViewModel.getAll(1)
        }
    }

    private fun initFavoriteObserver() {

        lifecycleScope.launch {
            favoriteViewModel.favorite.collectLatest { favoriteUiState ->
                favoriteAdapter.submitList(favoriteUiState.favoriteLocals.toMutableList())
            }
        }
    }

    private fun deleteFavorite(favoriteLocal: FavoriteLocal) {

        val alert = AlertDialog.Builder(requireContext())

        alert.setTitle("Remove")
        alert.setMessage("Are you sure you want to remove this product from favorites?")

        alert.setPositiveButton("Yes") { _, _ ->
            favoriteViewModel.delete(favoriteLocal)
        }
        alert.setNegativeButton("No") { _, _ -> }
        alert.show()


    }
}