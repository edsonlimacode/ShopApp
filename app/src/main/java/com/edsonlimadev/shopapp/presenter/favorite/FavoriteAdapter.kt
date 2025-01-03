package com.edsonlimadev.shopapp.presenter.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.edsonlimadev.shopapp.databinding.ItemFavoriteBinding
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal


class FavoriteAdapter(
    val onClickRemove: (FavoriteLocal) -> Unit
) : ListAdapter<FavoriteLocal, FavoriteAdapter.FavoriteViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FavoriteLocal>() {
            override fun areItemsTheSame(oldItem: FavoriteLocal, newItem: FavoriteLocal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteLocal, newItem: FavoriteLocal): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) : ViewHolder(
        binding.root
    ) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemFavoriteBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(view)

    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        with(holder) {
            Glide.with(holder.itemView.context).load(favorite.image)
                .into(binding.imgProductImageHome)
            binding.textProductTitleFavorite.text = favorite.title
            binding.textProductPriceFavorite.text = favorite.price.toString()

            binding.btnRemoveFavorite.setOnClickListener {
                onClickRemove(favorite)
            }
        }


    }

}