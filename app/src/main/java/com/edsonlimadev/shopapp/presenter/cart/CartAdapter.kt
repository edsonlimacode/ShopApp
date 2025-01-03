package com.edsonlimadev.shopapp.presenter.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.edsonlimadev.shopapp.databinding.ItemCartBinding
import com.edsonlimadev.shopapp.domain.local.ProductLocal

class CartAdapter(
    private val onRemoveProduct: (ProductLocal) -> Unit,
    private val onAddItem: (ProductLocal) -> Unit,
    private val onRemoveItem: (ProductLocal) -> Unit
) : ListAdapter<ProductLocal, CartAdapter.CartViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductLocal>() {
            override fun areItemsTheSame(oldItem: ProductLocal, newItem: ProductLocal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductLocal, newItem: ProductLocal): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class CartViewHolder(val binding: ItemCartBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            Glide.with(itemView.context).load(item.image).into(binding.imgProductImageCart)
            binding.textProductTitleCart.text = item.title
            binding.textProductPriceCart.text = (item.price?.times(item.quantity)).toString()
            binding.textQnt.text = item.quantity.toString()


            binding.btnRemoveCart.setOnClickListener {
                onRemoveProduct(item)
            }

            binding.btnAddQnt.setOnClickListener {
                onAddItem(item)
            }

            binding.btnMinusQnt.setOnClickListener {
                onRemoveItem(item)
            }
        }
    }

}