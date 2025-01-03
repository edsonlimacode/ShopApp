package com.edsonlimadev.shopapp.presenter.category

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.edsonlimadev.shopapp.R
import com.edsonlimadev.shopapp.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val context: Context,
    private val onClickListener: (String) -> Unit
) : Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition: Int = -1

    private val categories = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategories(categories: List<String>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = ItemCategoryBinding.inflate(inflater, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

        val category = categories[position]


        with(holder) {
            binding.btnCategory.text = category

            holder.binding.btnCategory.setBackgroundColor(
                if (position == selectedPosition) context.getColor(R.color.primaryDefault) else Color.WHITE
            )

            binding.btnCategory.setOnClickListener {

                notifyItemChanged(selectedPosition)
                selectedPosition = position
                notifyItemChanged(selectedPosition)

                onClickListener(category)
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}