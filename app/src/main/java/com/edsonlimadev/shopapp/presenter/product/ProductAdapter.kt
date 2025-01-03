package com.edsonlimadev.shopapp.presenter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.edsonlimadev.shopapp.databinding.ItemProdutcBinding
import com.edsonlimadev.shopapp.domain.model.Product

class ProductAdapter(
    private val onClickListener: (Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ProductViewHolder(
        val binding: ItemProdutcBinding
    ) : ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = ItemProdutcBinding.inflate(inflater, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        with(holder) {
            Glide.with(binding.root.context).load(product.image).into(binding.imgProductImageHome)
            binding.textProductTitleHome.text = product.title
            binding.textProductPriceHome.text = product.price
        }

        holder.itemView.setOnClickListener {
            onClickListener(product.id)
        }
    }

}