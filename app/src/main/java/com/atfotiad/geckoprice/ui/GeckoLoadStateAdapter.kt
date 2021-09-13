package com.atfotiad.geckoprice.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atfotiad.geckoprice.databinding.LoadStateCryptoItemBinding

class GeckoLoadStateAdapter (
     val retry: () -> Unit
        ): LoadStateAdapter<GeckoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            LoadStateCryptoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadStateViewHolder(private val binding: LoadStateCryptoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error){
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.packageProgressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
            binding.retryButton.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
            binding.errorMsg.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE

            binding.retryButton.setOnClickListener {
                (bindingAdapter as GeckoLoadStateAdapter).retry.invoke()
            }
        }

    }
}