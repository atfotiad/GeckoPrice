package com.atfotiad.geckoprice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.atfotiad.geckoprice.databinding.ItemGeckoBinding
import com.atfotiad.geckoprice.model.Coin
import com.bumptech.glide.Glide

class GeckoCoinAdapter : PagingDataAdapter<Coin, GeckoCoinAdapter.CoinViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemGeckoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CoinViewHolder(private val binding: ItemGeckoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            binding.apply {
                tvFullName.text = coin.name
                tvName.text = coin.symbol
                tvPrice.text = coin.current_price.toString().plus(" €")
                Glide.with(itemView).load(coin.image)
                    .circleCrop()
                    .into(cryptoImg)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem == newItem
            }
        }

    }

}