package com.hellbrandsdigital.android_template.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hellbrandsdigital.android_template.model.entity.Item
import com.hellbrandsdigital.androidtemplate.databinding.ItemlistAdapterItemBinding

class ItemListAdapter(
    private val onClick: (Pair<Item, View>) -> Unit
) :
    ListAdapter<Item, ItemListAdapter.ItemListViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(
            ItemlistAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class ItemListViewHolder(
        private var binding: ItemlistAdapterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, onClick: (Pair<Item, View>) -> Unit) {
            binding.itemCheckBox.text = item.name
            binding.category.text = item.category

            binding.itemCheckBox.setOnClickListener {
                onClick(Pair(item, it))
            }
        }
    }
}
